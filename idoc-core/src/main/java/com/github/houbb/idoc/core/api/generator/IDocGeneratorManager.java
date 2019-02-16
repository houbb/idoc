package com.github.houbb.idoc.core.api.generator;

import com.github.houbb.idoc.api.core.genenrator.IDocGenerator;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.config.IDocConfig;
import com.github.houbb.idoc.common.exception.IDocRuntimeException;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.ftl.api.generator.MarkdownDocGenerator;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import org.apache.maven.project.MavenProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class IDocGeneratorManager implements IDocGenerator {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(IDocGeneratorManager.class);

    /**
     * maven 项目配置信息
     */
    private final MavenProject mavenProject;

    /**
     * 文档配置
     */
    final IDocConfig docConfig;

    /**
     * 文档生成列表
     */
    private List<IDocGenerator> docGeneratorList;

    public IDocGeneratorManager(MavenProject mavenProject, final IDocConfig docConfig) {
        this.docConfig = docConfig;
        this.mavenProject = mavenProject;
        this.initDocGenerators();
    }

    @Override
    public void generate(Collection<DocClass> docClasses) {
        for (IDocGenerator docGenerator : docGeneratorList) {
            log.info("Generator doc with docGenerator: {}", docGenerator.getClass().getName());
            docGenerator.generate(docClasses);
        }
    }

    /**
     * 初始化文档生成器
     */
    private void initDocGenerators() {
        final String[] generates = docConfig.getGenerates();
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
                        MarkdownDocGenerator markdownDocGenerator = new MarkdownDocGenerator(mavenProject, docConfig);
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
}
