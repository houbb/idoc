package com.github.houbb.idoc.core.core.impl;

import com.github.houbb.idoc.api.core.IDocGenerator;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.core.api.generator.ConsoleDocGenerator;
import com.github.houbb.idoc.core.api.generator.MarkdownDocGenerator;
import com.github.houbb.idoc.core.constant.IDocConstant;
import com.github.houbb.idoc.core.exception.IDocRuntimeException;
import com.github.houbb.idoc.core.handler.JavaClassHandler;
import com.github.houbb.idoc.core.handler.impl.GenerateDocClassHandler;
import com.github.houbb.idoc.core.util.ArrayUtil;
import com.github.houbb.idoc.core.util.FreemarkerUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.paradise.common.constant.MavenConstant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public class GenerateDocService extends AbstractExecuteService {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(GenerateDocService.class);

    /**
     * 文档生成列表
     */
    private List<IDocGenerator> docGeneratorList;

    /**
     * 生成模板
     */
    private Template template;

    /**
     * 生成器配置
     */
    private final String[] generates;

    /**
     *  抽象执行服务
     *
     * @param mavenProject maven项目
     * @param encoding     编码
     */
    public GenerateDocService(MavenProject mavenProject, String encoding, final String[] generates) {
        super(mavenProject, encoding);
        this.generates = generates;
        log.debug("Initial generate with project: {} and encoding: {}, generates：{}",
                mavenProject, encoding, Arrays.toString(generates));
    }

    /**
     * 初始化文档生成器
     *
     * @param generates 生成类全称
     */
    private void initDocGenerators(final String[] generates) {
        final MarkdownDocGenerator markdownDocGenerator = new MarkdownDocGenerator(template, project,
                true, true);
        if (ArrayUtil.isEmpty(generates)) {
            docGeneratorList = new ArrayList<>(1);
            docGeneratorList.add(markdownDocGenerator);
        } else {
            docGeneratorList = new ArrayList<>(generates.length);
            try {
                for (String string : generates) {
                    IDocGenerator docGenerator = (IDocGenerator) Class.forName(string).newInstance();
                    //设置 markdown 对应的模板信息
                    if (docGenerator instanceof MarkdownDocGenerator) {
                        docGeneratorList.add(markdownDocGenerator);
                    } else {
                        docGeneratorList.add(docGenerator);
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                log.error("Initial docGeneratorList meet ex: {}", e, e);
                throw new IDocRuntimeException(e);
            }
        }
    }

    @Override
    protected JavaClassHandler configJavaClassHandler() {
        JavaClassHandler classHandler = new GenerateDocClassHandler();
        log.debug("Initial with java class handler: {}", classHandler.getClass().getName());
        return classHandler;
    }

    @Override
    protected void beforeExecute() throws IDocRuntimeException {
        //1. 初始化配置
        initConfig();

        // 初始化配置
        this.initDocGenerators(generates);
    }

    @Override
    protected void afterExecute(List<DocClass> docClassList) throws IDocRuntimeException {
        for (IDocGenerator docGenerator : docGeneratorList) {
            log.info("Generator doc with docGenerator: {}", docGenerator.getClass().getName());
            docGenerator.generate(docClassList);
        }
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        try {
            Configuration configuration = FreemarkerUtil.getConfiguration(encoding, true);

            //1. 判断根目录下是否有此文件 如果有则按照这个为准
            final String userDefineFtl = project.getBasedir().getPath() + File.separator
                    + MavenConstant.SRC_MAIN_RESOURCES_PATH + IDocConstant.IDOC_MARKDOWN_ALL_IN_ONE_FTL;
            Path path = Paths.get(userDefineFtl);
            if (Files.exists(path)) {
                // 用户根目录自定义
                configuration.setDirectoryForTemplateLoading(new File(project.getBasedir().getPath()
                        + File.separator + MavenConstant.SRC_MAIN_RESOURCES_PATH));
            } else {
                configuration.setClassForTemplateLoading(FreemarkerUtil.class,
                        IDocConstant.IDOC_MARKDOWN_BASE_PACKAGE);
            }

            template = configuration.getTemplate(IDocConstant.IDOC_MARKDOWN_ALL_IN_ONE_FTL);
        } catch (IOException e) {
            log.error("init config meet ex:{}", e);
            throw new IDocRuntimeException(e);
        }
    }

}
