package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocClass;
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
public class MetadataDocReturnClassHandler implements IHandler<JavaMethod, DocClass> {

    @Override
    public DocClass handle(JavaMethod javaMethod) {
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
