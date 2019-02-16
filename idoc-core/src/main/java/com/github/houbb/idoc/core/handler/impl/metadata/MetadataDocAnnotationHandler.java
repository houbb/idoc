package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocAnnotation;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.thoughtworks.qdox.model.Annotation;

/**
 * 处理注解
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocAnnotationHandler extends AbstractHandler<Annotation, DocAnnotation> {
    @Override
    protected DocAnnotation doHandle(Annotation target) {
        DocAnnotation docAnnotation = new DocAnnotation();
        //TODO 具体的实现
        return docAnnotation;
    }
}
