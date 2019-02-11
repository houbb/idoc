package com.github.houbb.idoc.api.core;

import com.github.houbb.idoc.api.model.metadata.DocClass;

import java.util.Collection;

/**
 * 文档生成类接口
 * 1. 控制台
 * 2. markdown
 * 3. html
 * 4. word
 *
 * 用户可以使用系统内置的，也可以使用自定义的实现。
 * @author binbin.hou
 * date 2019/2/11
 */
public interface IDocGenerator {

    /**
     * 生成文档信息
     * @param docClasses 文档类原始信息
     */
    void generate(final Collection<DocClass> docClasses);

}
