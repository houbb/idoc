package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.util.ObjectUtil;
import com.github.houbb.idoc.core.constant.JavaTagConstant;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

/**
 * 处理方法的返回值对象
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocReturnClassHandler extends AbstractHandler<JavaMethod, DocClass> {
    @Override
    protected DocClass doHandle(JavaMethod javaMethod) {
        // 如果有一个方法，在方法体中返回当前类。就会导致死循环
        JavaClass returnClass = javaMethod.getReturnType() == null ? null : javaMethod.getReturnType().getJavaClass();
        DocClass docClass = new MetadataDocClassHandler().handle(returnClass);

        // 注释
        DocletTag docletTag = javaMethod.getTagByName(JavaTagConstant.RETURN);
        if (ObjectUtil.isNotNull(docletTag)) {
            docClass.setMethodComment(docletTag.getValue());
        }
        return docClass;
    }

}
