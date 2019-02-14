package com.github.houbb.idoc.api.core.filter;

import com.github.houbb.idoc.api.model.metadata.DocClass;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IDocExcludeFilter {

    /**
     * 是否排除
     * @param docClass 类信息
     * @return 是否排除
     */
    boolean exclude(final DocClass docClass);

}
