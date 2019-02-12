package com.github.houbb.idoc.core.api.generator;

import com.alibaba.fastjson.JSON;
import com.github.houbb.idoc.api.core.IDocGenerator;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.paradise.common.util.CollectionUtil;

import java.util.Collection;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public class ConsoleDocGenerator implements IDocGenerator {
    @Override
    public void generate(Collection<DocClass> docClasses) {
        for(DocClass docClass : docClasses) {
            System.out.println("===========================类:" + docClass.getFullName());
            System.out.println("-------------------------信息:"
                    + JSON.toJSONString(docClass));
            System.out.println();
        }
    }
}
