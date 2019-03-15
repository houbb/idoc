package com.github.houbb.idoc.test.filter;

import com.github.houbb.idoc.api.core.filter.IDocGenerateFilter;
import com.github.houbb.idoc.api.model.metadata.DocClass;

/**
 * 自定义生成过滤器
 * @author binbin.hou
 * @since 0.1.0
 */
public class MyGenerateAliasFilter implements IDocGenerateFilter {

    @Override
    public boolean include(DocClass docClass) {
        if("TypeAliasSimpleBean".equalsIgnoreCase(docClass.getName())) {
            return true;
        }
        return false;
    }

}
