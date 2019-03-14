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
 * @since 0.0.1
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

    private MetadataDocUtil(){}

}
