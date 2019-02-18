package com.github.houbb.idoc.core.api.generator;

import com.github.houbb.idoc.api.core.genenrator.IDocGenerator;
import com.github.houbb.idoc.api.model.config.DocConfig;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.mvn.DocMavenProject;
import com.github.houbb.idoc.common.exception.IDocRuntimeException;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 文档生成器管理类
 * @author binbin.hou
 * @since 0.0.1
 */
public class IDocGeneratorManager implements IDocGenerator {

    /**
     * 日志GenerateDocMojo
     */
    private final Log log = LogFactory.getLog(IDocGeneratorManager.class);

    @Override
    public void generate(DocMavenProject docMavenProject, DocConfig docConfig, Collection<DocClass> docClasses) {
        //1. 初始化文档生成器
        List<IDocGenerator> docGeneratorList = this.initDocGenerators(docConfig);

        //2. 依次生成文档信息
        for (IDocGenerator docGenerator : docGeneratorList) {
            log.info("Generator doc with docGenerator: {}", docGenerator.getClass().getName());
            docGenerator.generate(docMavenProject, docConfig, docClasses);
        }
    }

    /**
     * 初始化文档生成器
     * @param docConfig 文档配置信息
     * @return 文档生成列表
     */
    private List<IDocGenerator> initDocGenerators(final DocConfig docConfig) {
        final String[] generates = docConfig.getGenerates();
        List<IDocGenerator> docGeneratorList = new ArrayList<>();

        if (ArrayUtil.isEmpty(generates)) {
            final ConsoleDocGenerator consoleDocGenerator = new ConsoleDocGenerator();
            docGeneratorList.add(consoleDocGenerator);
        } else {
            docGeneratorList = new ArrayList<>(generates.length);
            try {
                for (String string : generates) {
                    IDocGenerator docGenerator = (IDocGenerator) Class.forName(string).newInstance();
                    docGeneratorList.add(docGenerator);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                log.error("Initial docGeneratorList meet ex: {}", e, e);
                throw new IDocRuntimeException(e);
            }
        }
        return docGeneratorList;
    }

}
