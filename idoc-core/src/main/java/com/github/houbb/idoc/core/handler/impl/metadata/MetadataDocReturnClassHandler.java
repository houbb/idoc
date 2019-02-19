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

    /**
     * 当前方法对应的类信息
     */
    private final DocClass docClass;

    public MetadataDocReturnClassHandler(DocClass docClass) {
        this.docClass = docClass;
    }

    @Override
    protected DocClass doHandle(JavaMethod javaMethod) {
        // 如果有一个方法，在方法体中返回当前类。就会导致死循环
        // void 对应的返回类型是什么？
        JavaClass returnClass = javaMethod.getReturnType() == null ? null : javaMethod.getReturnType().getJavaClass();
        // 这个地方可以单独使用 DocReturnClass 对象
        DocClass docReturnClass = new DocClass();
        if(isDeadCycle(docClass, returnClass)) {
            // 对象属性赋值

        } else {
            docReturnClass = new MetadataDocClassHandler().handle(returnClass);
        }

        // 注释
        DocletTag docletTag = javaMethod.getTagByName(JavaTagConstant.RETURN);
        if (ObjectUtil.isNotNull(docletTag)) {
            docClass.setMethodComment(docletTag.getValue());
        }
        return docClass;
    }

    /**
     * 是否为死循环
     * @param originalClass 原始类
     * @param returnClass 返回类
     * @return 是否
     */
    private boolean isDeadCycle(final DocClass originalClass, JavaClass returnClass) {
        if(originalClass.getFullName().equals(returnClass.getFullyQualifiedName())) {
            return true;
        }
        return false;
    }

}
