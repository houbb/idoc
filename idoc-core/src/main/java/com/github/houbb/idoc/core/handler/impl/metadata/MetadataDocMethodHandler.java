package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.config.DocConfig;
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

    /**
     * 当前方法对应的类信息
     */
    private final DocClass docClass;

    /**
     * 当前类配置信息
     */
    private final DocConfig docConfig;

    public MetadataDocMethodHandler(DocClass docClass, DocConfig docConfig) {
        this.docClass = docClass;
        this.docConfig = docConfig;
    }

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

        // tags 信息
        DocletTag[] docletTags = javaMethod.getTags();
        List<DocTag> docTagList = MetadataDocUtil.buildDocTagList(docletTags);
        docMethod.setDocTagList(docTagList);

        // 返回类型
        final DocMethodReturn docReturnClass = new MetadataDocReturnClassHandler(docClass, docConfig).handle(javaMethod);
        docMethod.setDocMethodReturn(docReturnClass);

        // 参数信息
        final List<DocMethodParameter> docMethodParameterList = new MetadataDocMethodParameterHandler(docClass, docConfig).handle(javaMethod);
        docMethod.setDocMethodParameterList(docMethodParameterList);

        // @see 列表信息添加
        DocletTag[] seeDocletTags = javaMethod.getTagsByName(JavaTagConstant.SEE);
        List<DocClass> seeDocClassList = ArrayUtil.buildList(seeDocletTags, new MetadataDocSeeListHandler());
        docMethod.setSeeList(seeDocClassList);

        // 抛出的异常信息列表
        DocletTag[] throwDocletTags = javaMethod.getTagsByName(JavaTagConstant.THROWS);
        List<DocClass> throwDocClassList = ArrayUtil.buildList(throwDocletTags, new MetadataDocSeeListHandler());
        docMethod.setExceptionList(throwDocClassList);

        return docMethod;
    }

}
