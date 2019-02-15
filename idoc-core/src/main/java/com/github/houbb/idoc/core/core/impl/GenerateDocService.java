package com.github.houbb.idoc.core.core.impl;

import com.github.houbb.idoc.api.core.genenrator.IDocGenerator;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.exception.IDocRuntimeException;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.core.api.generator.ConsoleDocGenerator;
import com.github.houbb.idoc.core.handler.JavaClassHandler;
import com.github.houbb.idoc.core.handler.impl.GenerateDocClassHandler;
import com.github.houbb.idoc.ftl.api.generator.MarkdownDocGenerator;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import org.apache.maven.project.MavenProject;

import java.util.ArrayList;
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
     * 文档生成列表
     */
    private List<IDocGenerator> docGeneratorList;

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
        if (ArrayUtil.isEmpty(generates)) {
            final ConsoleDocGenerator consoleDocGenerator = new ConsoleDocGenerator();
            docGeneratorList = new ArrayList<>(1);
            docGeneratorList.add(consoleDocGenerator);
        } else {
            docGeneratorList = new ArrayList<>(generates.length);
            try {
                for (String string : generates) {
                    IDocGenerator docGenerator = (IDocGenerator) Class.forName(string).newInstance();
                    //设置 markdown 对应的模板信息
                    if (docGenerator instanceof MarkdownDocGenerator) {
                        MarkdownDocGenerator markdownDocGenerator = new MarkdownDocGenerator(project, encoding);
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

}
