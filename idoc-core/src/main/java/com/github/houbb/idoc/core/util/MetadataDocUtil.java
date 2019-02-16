package com.github.houbb.idoc.core.util;

import com.github.houbb.idoc.api.model.metadata.DocAnnotation;
import com.github.houbb.idoc.api.model.metadata.DocField;
import com.github.houbb.idoc.api.model.metadata.DocMethod;
import com.github.houbb.idoc.api.model.metadata.DocTag;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.core.handler.impl.metadata.MetadataDocAnnotationHandler;
import com.github.houbb.idoc.core.handler.impl.metadata.MetadataDocFieldHandler;
import com.github.houbb.idoc.core.handler.impl.metadata.MetadataDocMethodHandler;
import com.github.houbb.idoc.core.handler.impl.metadata.MetadataDocTagHandler;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;

import java.util.List;

/**
 * @author binbin.hou
 * date 2019/2/16
 */
public final class MetadataDocUtil {


    /**
     * 构建标签列表
     * @param docletTags 标签
     * @return 结果
     */
    public static List<DocTag> buildDocTagList(final DocletTag[] docletTags) {
        return ArrayUtil.buildList(docletTags, new MetadataDocTagHandler());
    }

    /**
     * 构建注解列表
     * @param annotations 注解
     * @return 结果
     */
    public static List<DocAnnotation> buildDocAnnotationList(final Annotation[] annotations) {
        return ArrayUtil.buildList(annotations, new MetadataDocAnnotationHandler());
    }

    /**
     * 构建字段列表
     * @param javaFields 字段
     * @return 结果
     */
    public static List<DocField> buildDocFieldList(final JavaField[] javaFields) {
        return ArrayUtil.buildList(javaFields, new MetadataDocFieldHandler());
    }

    /**
     * 构建字段列表
     * @param javaFields 字段
     * @return 结果
     */
    public static List<DocField> buildDocFieldList(final List<JavaField> javaFields) {
        return CollectionUtil.buildList(javaFields, new MetadataDocFieldHandler());
    }

    /**
     * 构建方法
     * @param javaMethods 方法
     * @return 结果
     */
    public static List<DocMethod> buildDocMethodList(final JavaMethod[] javaMethods) {
        return ArrayUtil.buildList(javaMethods, new MetadataDocMethodHandler());
    }

    private MetadataDocUtil(){}

}