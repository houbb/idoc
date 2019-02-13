package com.github.houbb.idoc.core.api.generator;

import com.github.houbb.idoc.api.core.IDocGenerator;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.core.constant.IDocConstant;
import com.github.houbb.idoc.core.core.impl.GenerateDocService;
import com.github.houbb.idoc.core.util.FreemarkerUtil;
import com.github.houbb.idoc.core.util.SystemUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.paradise.common.constant.MavenConstant;
import com.github.houbb.paradise.common.util.DateUtil;
import freemarker.template.Template;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author binbin.hou
 * date 2019/2/11
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
     * 项目信息
     */
    private MavenProject project;

    /**
     * 是否覆盖编写
     */
    private boolean isOverwriteWhenExists;

    /**
     * 所有的内容生成一个文件
     */
    private boolean isAllInOne;

    public MarkdownDocGenerator(Template template, MavenProject project,
                                boolean isOverwriteWhenExists, boolean isAllInOne) {
        this.template = template;
        this.project = project;
        this.isOverwriteWhenExists = isOverwriteWhenExists;
        this.isAllInOne = isAllInOne;
    }

    /**
     * 生成的时候有两种模式：
     * 1. all-in-one 所有的信息在一起
     * 2. 每个类一个文件，然后生成一个统一的 index.md
     * @param docClasses 文档类原始信息
     */
    @Override
    public void generate(Collection<DocClass> docClasses) {
        String targetPath = buildTargetDirPath();

        //1. 生成目标文件夹
        Path path = Paths.get(targetPath);
        File file = path.toFile();
        boolean makeDirs = file.mkdirs();

        //2.0 生成对应的文件

        Map<String, Object> root = new HashMap<>();
        root.put("author", SystemUtil.getCurrentUserName());
        root.put("version", project.getVersion());
        root.put("today", DateUtil.getSimpleDateStr());

        // 这里等后期才变成可以自由配置的
        String targetFile = targetPath + IDocConstant.IDOC_MARKDOWN_ALL_IN_ONE;
        log.info("Markdown 生成文件信息: {}", targetFile);
        FreemarkerUtil.createFile(template, targetFile, root, isOverwriteWhenExists);
    }

    /**
     * 构建目标文件夹
     * TODO: 建议生成在 resource 目录下。
     * @return 目录
     */
    private String buildTargetDirPath() {
        return project.getBasedir().getPath() + File.separator + IDocConstant.IDOC_GEN_BASE_PACAKGE
                +File.separator;
    }

}
