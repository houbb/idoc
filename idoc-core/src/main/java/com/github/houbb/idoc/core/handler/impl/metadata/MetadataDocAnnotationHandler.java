package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocAnnotation;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;

/**
 * 处理注解
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocAnnotationHandler extends AbstractHandler<Annotation, DocAnnotation> {

    /**
     * 处理注解信息
     * @since 0.0.2 开始才进行的注解信息处理
     * @param target 原始对象
     * @return 处理后的注解信息
     */
    @Override
    @SuppressWarnings("all")
    protected DocAnnotation doHandle(Annotation target) {
        DocAnnotation docAnnotation = new DocAnnotation();
        final JavaClass javaClass = target.getType().getJavaClass();

        docAnnotation.setNamedParameters(target.getNamedParameterMap());
        docAnnotation.setProperties(target.getPropertyMap());
        //TODO: 其他属性信息
        return docAnnotation;
    }

}
