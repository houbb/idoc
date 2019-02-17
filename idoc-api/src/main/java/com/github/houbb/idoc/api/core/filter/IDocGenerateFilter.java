package com.github.houbb.idoc.api.core.filter;

import com.github.houbb.idoc.api.model.metadata.DocClass;

/**
 * 包含过滤器
 *
 * 用户可以指定当前类是否生成在结果文件中
 * 1. 比如只生成有 methods 的类
 * 2. 只生成指定版本的类
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IDocGenerateFilter {

    /**
     * 是否包含
     * @param docClass 类信息
     * @return 是否包含
     */
    boolean include(final DocClass docClass);

}
