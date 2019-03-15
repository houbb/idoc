package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.config.DocConfig;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.metadata.DocField;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.core.util.JavaClassUtil;
import com.github.houbb.idoc.core.util.MetadataDocUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.thoughtworks.qdox.model.*;

import java.util.List;

/**
 * 处理单个的类文件
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocClassHandler extends AbstractHandler<JavaClass, DocClass> {

    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(MetadataDocClassHandler.class);


    /**
     * 配置信息
     */
    private final DocConfig docConfig;

    public MetadataDocClassHandler(DocConfig docConfig) {
        this.docConfig = docConfig;
    }

    @Override
    protected DocClass doHandle(JavaClass javaClass) {
        // 原始基础信息
        DocClass docClass = new DocClass();
        docClass.setName(javaClass.getName());
        docClass.setPackageName(javaClass.getPackageName());
        docClass.setFullName(javaClass.getFullyQualifiedName());

        // java 默认的字段
        // TODO: 这里直接 return 应该是为了避免死循环和额外处理
        if (JavaClassUtil.isPrimitiveOrJdk(javaClass.asType())) {
            return docClass;
        }

        // 类基础信息
        docClass.setModifiers(javaClass.getModifiers());
        docClass.setComment(javaClass.getComment());

        // 类-标签信息
        final DocletTag[] docletTagArray = javaClass.getTags();
        docClass.setDocTagList(MetadataDocUtil.buildDocTagList(docletTagArray));

        // 类-注解信息
        final Annotation[] annotations = javaClass.getAnnotations();
        docClass.setDocAnnotationList(MetadataDocUtil.buildDocAnnotationList(annotations));

        // 字段信息
        final List<JavaField> javaFieldList = JavaClassUtil.getAllJavaFieldList(javaClass);
        docClass.setDocFieldList(CollectionUtil.buildList(javaFieldList, new MetadataDocFieldHandler(docConfig)));

        // 方法信息
        final JavaMethod[] javaMethods = javaClass.getMethods();
        docClass.setDocMethodList(ArrayUtil.buildList(javaMethods, new MetadataDocMethodHandler(docClass, docConfig)));

        return docClass;
    }

    /**
     * 构建字段列表
     * @param javaFields 字段
     * @return 结果
     */
    public List<DocField> buildDocFieldList(final List<JavaField> javaFields) {
        return CollectionUtil.buildList(javaFields, new MetadataDocFieldHandler(docConfig));
    }
}
