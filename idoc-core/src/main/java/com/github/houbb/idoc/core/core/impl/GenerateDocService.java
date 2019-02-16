package com.github.houbb.idoc.core.core.impl;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.config.IDocConfig;
import com.github.houbb.idoc.common.exception.IDocRuntimeException;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.core.api.generator.IDocGeneratorManager;
import com.github.houbb.idoc.core.filter.include.IDocGenerateFilterManager;
import com.github.houbb.idoc.core.handler.impl.metadata.MetadataDocClassHandler;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class GenerateDocService extends AbstractExecuteService {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(GenerateDocService.class);

    /**
     * 配置
     */
    private final IDocConfig docConfig;

    /**
     * 抽象执行服务
     *
     * @param mavenProject maven项目
     * @param docConfig  配置
     */
    public GenerateDocService(MavenProject mavenProject, final IDocConfig docConfig) {
        super(mavenProject, docConfig.getEncoding());

        this.docConfig = docConfig;
        log.debug("Initial generate with project: {} and encoding: {}, generates：{}, includeFilters: {}",
                mavenProject, docConfig.getEncoding(),
                Arrays.toString(docConfig.getGenerates()), Arrays.toString(docConfig.getGenerateFilters()));
    }



    @Override
    protected IHandler<JavaClass, DocClass> configJavaClassHandler() {
        IHandler<JavaClass, DocClass> classHandler = new MetadataDocClassHandler();
        log.debug("Initial with java class handler: {}", classHandler.getClass().getName());
        return classHandler;
    }

    @Override
    protected void beforeExecute() throws IDocRuntimeException {
        log.debug("beforeExecute....");
    }

    @Override
    protected void afterExecute(List<DocClass> docClassList) throws IDocRuntimeException {
        // 生成文件的过滤
        IDocGenerateFilterManager includeFilterManager = new IDocGenerateFilterManager(docConfig.getGenerateFilters());
        List<DocClass> filterDocClassList = includeFilterManager.filter(docClassList);

        // 文件的执行
        IDocGeneratorManager docGeneratorManager = new IDocGeneratorManager(project, docConfig);
        docGeneratorManager.generate(filterDocClassList);
    }
}
