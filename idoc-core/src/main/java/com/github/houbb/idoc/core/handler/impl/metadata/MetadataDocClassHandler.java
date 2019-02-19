package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.util.ArrayUtil;
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


    @Override
    protected DocClass doHandle(JavaClass javaClass) {
        // 原始基础信息
        DocClass docClass = new DocClass();
        docClass.setName(javaClass.getName());
        docClass.setPackageName(javaClass.getPackageName());
        docClass.setFullName(javaClass.getFullyQualifiedName());

        // java 默认的字段
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
        docClass.setDocFieldList(MetadataDocUtil.buildDocFieldList(javaFieldList));

        // 方法信息
        final JavaMethod[] javaMethods = javaClass.getMethods();
        docClass.setDocMethodList(ArrayUtil.buildList(javaMethods, new MetadataDocMethodHandler(docClass)));

        return docClass;
    }

}
