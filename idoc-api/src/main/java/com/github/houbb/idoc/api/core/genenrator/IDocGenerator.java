package com.github.houbb.idoc.api.core.genenrator;

import com.github.houbb.idoc.api.model.config.DocConfig;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.mvn.DocMavenProject;

import java.util.Collection;

/**
 * 文档生成类接口
 * <p>
 * 系统内置实现：
 * 1. 控制台
 * 2. markdown
 * 3. html
 * 4. word
 * 5. pdf
 * <p>
 * 1. 用户可以使用系统内置的，也可以使用自定义的实现。
 * 2. 一定要提供默认的无参构造器
 * <p>
 * 当用户自定义生成器的时候，所有的信息自己都是知道的。没必要在配置一遍。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IDocGenerator {

    /**
     * 生成文档信息
     *
     * @param docClasses      文档类原始信息
     * @param docConfig       配置信息
     * @param docMavenProject maven 项目信息
     */
    void generate(final DocMavenProject docMavenProject,
                  final DocConfig docConfig,
                  final Collection<DocClass> docClasses);

}
