package com.github.houbb.idoc.core.core.impl;

import com.github.houbb.idoc.core.exception.IDocRuntimeException;
import com.github.houbb.idoc.core.handler.JavaClassHandler;
import com.github.houbb.idoc.core.handler.impl.GenerateDocClassHandler;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public class GenerateDocService extends AbstractExecuteService{

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(GenerateDocService.class);

    /**
     * 实现类列表
     */
    private String[] generates;

    /**
     *  抽象执行服务
     *
     * @param mavenProject maven项目
     * @param encoding     编码
     */
    public GenerateDocService(MavenProject mavenProject, String encoding, final String[] generates) {
        super(mavenProject, encoding);
        this.initDocGenerators(generates);
        log.debug("Initial generate with project: {} and encoding: {}, generates：{}",
                mavenProject, encoding, Arrays.toString(generates));
    }

    /**
     * 初始化文档生成器
     * @param generates 生成类全称
     */
    private void initDocGenerators(final String[] generates) {

    }

    @Override
    protected JavaClassHandler configJavaClassHandler() {
        JavaClassHandler classHandler = new GenerateDocClassHandler();
        log.debug("Initial with java class handler: {}", classHandler.getClass().getName());
        return classHandler;
    }

    @Override
    protected void beforeExecute() throws IDocRuntimeException {
        log.debug("Before execute...");
    }

    @Override
    protected void afterExecute() throws IDocRuntimeException {
        log.debug("After execute...");
    }
}
