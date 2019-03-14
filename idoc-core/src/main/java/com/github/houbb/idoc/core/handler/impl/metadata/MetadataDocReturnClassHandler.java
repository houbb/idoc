package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.config.DocConfig;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.metadata.DocMethodReturn;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.util.ObjectUtil;
import com.github.houbb.idoc.core.constant.JavaTagConstant;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.Type;

/**
 * 处理方法的返回值对象
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocReturnClassHandler extends AbstractHandler<JavaMethod, DocMethodReturn> {

    /**
     * 当前方法对应的类信息
     */
    private final DocClass docClass;

    /**
     * 配置信息
     */
    private final DocConfig docConfig;

    public MetadataDocReturnClassHandler(DocClass docClass, DocConfig docConfig) {
        this.docClass = docClass;
        this.docConfig = docConfig;
    }

    @Override
    protected DocMethodReturn doHandle(JavaMethod javaMethod) {
        JavaClass returnClass = isVoidReturnType(javaMethod) ? null : javaMethod.getReturnType().getJavaClass();
        DocMethodReturn docMethodReturn = new DocMethodReturn();

        // 注释
        DocletTag docletTag = javaMethod.getTagByName(JavaTagConstant.RETURN);
        if (ObjectUtil.isNotNull(docletTag)) {
            docMethodReturn.setReturnComment(docletTag.getValue());
        }

        // 方法的返回值为空，直接返回
        if(ObjectUtil.isNull(returnClass)) {
            return docMethodReturn;
        }

        // 如果有一个方法，在方法体中返回当前类。就会导致死循环
        // 这个地方可以单独使用 DocReturnClass 对象
        if(isDeadCycle(docClass, returnClass)) {
            // 对象属性赋值,直接将 doclass 信息设置过来
            fillDocMethodReturn(docMethodReturn, docClass);
        } else {
            DocClass docClass = new MetadataDocClassHandler(docConfig).handle(returnClass);
            fillDocMethodReturn(docMethodReturn, docClass);
        }

        return docMethodReturn;
    }

    /**
     * 填充方法返回值的信息
     * @param docMethodReturn 方法返回值
     * @param docClass 类信息
     */
    private void fillDocMethodReturn(DocMethodReturn docMethodReturn,
                                                final DocClass docClass) {
        //TODO: 下个版本使用 BeanUtils 直接赋值
        docMethodReturn.setDocFieldList(docClass.getDocFieldList());
        docMethodReturn.setDocMethodList(docClass.getDocMethodList());
        docMethodReturn.setFullName(docClass.getFullName());
        docMethodReturn.setPackageName(docClass.getPackageName());
        docMethodReturn.setName(docClass.getName());
        docMethodReturn.setAuthorList(docClass.getAuthorList());
        docMethodReturn.setComment(docClass.getComment());
        docMethodReturn.setModifiers(docClass.getModifiers());
        docMethodReturn.setDocAnnotationList(docClass.getDocAnnotationList());
        docMethodReturn.setRemark(docClass.getRemark());
    }

    /**
     * 返回值为 void
     * @param javaMethod 方法
     * @return 是否为空返回值
     */
    private boolean isVoidReturnType(final JavaMethod javaMethod) {
        final Type type = javaMethod.getReturnType();
        return type == null || type.equals(Type.VOID);
    }

    /**
     * 是否为死循环
     * @param originalClass 原始类
     * @param returnClass 返回类
     * @return 是否
     */
    private boolean isDeadCycle(final DocClass originalClass, JavaClass returnClass) {
        return originalClass.getFullName().equals(returnClass.getFullyQualifiedName());
    }

}
