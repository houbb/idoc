package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.*;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.common.util.ObjectUtil;
import com.github.houbb.idoc.core.constant.JavaTagConstant;
import com.github.houbb.idoc.core.util.MetadataDocUtil;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaMethod;

import java.util.List;

/**
 * 处理方法
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocMethodHandler extends AbstractHandler<JavaMethod, DocMethod> {

    @Override
    protected DocMethod doHandle(JavaMethod javaMethod) {
        // 方法基础信息
        DocMethod docMethod = new DocMethod();
        docMethod.setName(javaMethod.getName());
        docMethod.setComment(javaMethod.getComment());
        docMethod.setSignature(javaMethod.getCallSignature());
        docMethod.setModifiers(javaMethod.getModifiers());
        DocletTag docletTag = javaMethod.getTagByName(JavaTagConstant.SINCE);
        if (ObjectUtil.isNotNull(docletTag)) {
            docMethod.setSince(docletTag.getValue());
        }

        // 返回类型
        final DocClass docReturnClass = new MetadataDocReturnClassHandler().handle(javaMethod);
        docMethod.setDocReturnClass(docReturnClass);

        // tags 信息
        DocletTag[] docletTags = javaMethod.getTags();
        List<DocTag> docTagList = MetadataDocUtil.buildDocTagList(docletTags);
        docMethod.setDocTagList(docTagList);

        // 参数信息
        final List<DocParameter> docParameterList = new MetadataDocParameterHandler().handle(javaMethod);
        docMethod.setDocParameterList(docParameterList);
        return docMethod;
    }
}
