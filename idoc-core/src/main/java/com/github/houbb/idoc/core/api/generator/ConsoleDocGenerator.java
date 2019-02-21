package com.github.houbb.idoc.core.api.generator;

import com.alibaba.fastjson.JSON;
import com.github.houbb.idoc.api.core.genenrator.IDocGenerator;
import com.github.houbb.idoc.api.model.config.DocConfig;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.mvn.DocMavenProject;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.core.core.impl.GenerateDocService;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;

import java.util.Collection;

/**
 * 命令行文档信息
 * @author binbin.hou
 * @since 0.0.1
 */
public class ConsoleDocGenerator implements IDocGenerator {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(GenerateDocService.class);

    @Override
    public void generate(DocMavenProject docMavenProject, DocConfig docConfig, Collection<DocClass> docClasses) {
        if(CollectionUtil.isEmpty(docClasses)) {
            log.info("------------------------------------ 文档列表为空");
        }

        log.info("------------------------------------ 文档信息如下：");
        for(DocClass docClass : docClasses) {
            System.out.println("[类名] " + docClass.getFullName());
            System.out.println("[类信息] " + JSON.toJSONString(docClass));
            System.out.println();
        }
    }

}
