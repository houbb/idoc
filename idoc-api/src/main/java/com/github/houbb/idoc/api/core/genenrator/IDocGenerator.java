package com.github.houbb.idoc.api.core.genenrator;

import com.github.houbb.idoc.api.config.IDocConfig;
import com.github.houbb.idoc.api.model.metadata.DocClass;

import java.util.Collection;

/**
 * 文档生成类接口
 *
 * 系统内置实现：
 * 1. 控制台
 * 2. markdown
 * 3. html
 * 4. word
 * 5. pdf
 *
 * 用户可以使用系统内置的，也可以使用自定义的实现。
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IDocGenerator {

    /**
     * 生成文档信息
     * @param docClasses 文档类原始信息
     */
    void generate(final Collection<DocClass> docClasses);

    /**
     * 获取文档配置信息
     * @return 配置信息
     */
    IDocConfig getDocConfig();

}
