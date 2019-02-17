package com.github.houbb.idoc.ftl.api.generator;

import com.github.houbb.idoc.api.core.genenrator.IDocGenerator;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.config.IDocConfig;
import com.github.houbb.idoc.common.exception.IDocRuntimeException;
import com.github.houbb.idoc.common.handler.impl.simplify.SimplifyClassHandler;
import com.github.houbb.idoc.common.model.SimplifyDocClass;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.common.util.SystemUtil;
import com.github.houbb.idoc.ftl.constant.MarkdownConstant;
import com.github.houbb.idoc.ftl.util.FreemarkerUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.paradise.common.constant.MavenConstant;
import com.github.houbb.paradise.common.util.DateUtil;
import com.github.houbb.paradise.common.util.PathUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * markdown 文档生成器
 * @author binbin.hou
 * @since 0.0.1
 */
public class MarkdownDocGenerator implements IDocGenerator {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(MarkdownDocGenerator.class);

    /**
     * 模板
     */
    private Template template;

    /**
     * 是否覆盖编写
     */
    private boolean isOverwriteWhenExists;

    /**
     * 所有的内容生成一个文件
     */
    private boolean isAllInOne;

    /**
     * 项目信息
     */
    private MavenProject project;

    /**
     * 编码
     */
    private String encoding;

    /**
     * 必须要有空构造器
     */
    public MarkdownDocGenerator() {
    }

    public MarkdownDocGenerator(final MavenProject project, final IDocConfig docConfig) {
        this.project = project;
        this.encoding = docConfig.getEncoding();
        this.isOverwriteWhenExists = docConfig.isOverwriteWhenExists();
        this.isAllInOne = docConfig.isAllInOne();

        // 初始化FTL配置
        initFtlConfig();
    }

    /**
     * 生成的时候有两种模式：
     * 1. all-in-one 所有的信息在一起
     * 2. 每个类一个文件，然后生成一个统一的 index.md
     *
     * TODO: 多线程处理
     * @param docClasses 文档类原始信息
     */
    @Override
    public void generate(Collection<DocClass> docClasses) {
        String genBaseDir = project.getBasedir().getPath()
                + File.separator
                + MarkdownConstant.Generate.IDOC_MARKDOWN_BASE_PACAKGE
                +File.separator;

        //2.0 生成对应的文件
        List<SimplifyDocClass> simplifyDocClasses = CollectionUtil.buildList(docClasses, new SimplifyClassHandler());
        if(CollectionUtil.isEmpty(simplifyDocClasses)) {
            log.info("待生成列表信息为空，生成文档结束。");
            return;
        }

        // 基础信息
        if(isAllInOne) {
            String targetFile = genBaseDir + project.getName()+"-全部文档.md";
            log.info("Markdown 生成文档文件 all in one 路径: {}", targetFile);
            StringBuilder stringBuffer = new StringBuilder();
            for(SimplifyDocClass javaClass : simplifyDocClasses) {
                final  String fileContent = getDocClassContent(javaClass);
                stringBuffer.append(fileContent).append("\n\n");
            }
            FreemarkerUtil.createFile(targetFile, isOverwriteWhenExists, stringBuffer.toString());
        } else {
            for(SimplifyDocClass javaClass : simplifyDocClasses) {
                // 在指定的文件夹下，指定的每一个类对应一个文件。
                // 这里的处理可以抽象出来，所有的实现只是具体的
                final String packageName = javaClass.getPackageName();
                final String className = javaClass.getName();
                String segmentTargetPathStr = genBaseDir + PathUtil.packageToPath(packageName) + File.separator;
                String targetFile = segmentTargetPathStr + className + ".md";
                final  String fileContent = getDocClassContent(javaClass);
                log.info("Markdown 生成文档文件单个类路径: {}", targetFile);
                FreemarkerUtil.createFile(targetFile, isOverwriteWhenExists, fileContent);
            }
        }
    }

    /**
     * 获取文档内容
     * @param simplifyDocClass 原始类信息
     * @return 结果
     */
    private String getDocClassContent(final SimplifyDocClass simplifyDocClass) {
        Map<String, Object> map = new HashMap<>();
        map.put("author", SystemUtil.getCurrentUserName());
        map.put("today", DateUtil.getSimpleDateStr());
        map.put("class", simplifyDocClass);
        return FreemarkerUtil.getTemplateContent(template, map);
    }

    /**
     * 初始化FTL配置
     * 用户自定义：resource/idoc-markdown-class-segment.ftl
     */
    private void initFtlConfig() {
        try {
            Configuration configuration = FreemarkerUtil.getConfiguration(encoding, true);

            //1. 判断根目录下是否有此文件 如果有则按照这个为准
            final String userDefineFtl = project.getBasedir().getPath() + File.separator
                    + MavenConstant.SRC_MAIN_RESOURCES_PATH +
                    MarkdownConstant.Template.IDOC_MARKDOWN_CLASS_SEGMENT_FTL;
            Path path = Paths.get(userDefineFtl);
            if (Files.exists(path)) {
                // 用户根目录自定义
                configuration.setDirectoryForTemplateLoading(new File(project.getBasedir().getPath()
                        + File.separator + MavenConstant.SRC_MAIN_RESOURCES_PATH));
            } else {
                configuration.setClassForTemplateLoading(FreemarkerUtil.class,
                        MarkdownConstant.Template.IDOC_MARKDOWN_BASE_PACKAGE);
            }

            template = configuration.getTemplate(MarkdownConstant.Template.IDOC_MARKDOWN_CLASS_SEGMENT_FTL);
        } catch (IOException e) {
            log.error("init config meet ex:{}", e);
            throw new IDocRuntimeException(e);
        }
    }
}
