package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocParameter;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.core.constant.JavaTagConstant;
import com.github.houbb.idoc.core.util.JavaClassUtil;
import com.github.houbb.idoc.core.util.MetadataDocUtil;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

import java.util.List;

/**
 * 处理方法参数
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocParameterHandler extends AbstractHandler<JavaMethod, List<DocParameter>> {

    @Override
    protected List<DocParameter> doHandle(final JavaMethod javaMethod) {
        final JavaParameter[] javaParameters = javaMethod.getParameters();
        return ArrayUtil.buildList(javaParameters, new IHandler<JavaParameter, DocParameter>() {
            @Override
            public DocParameter handle(JavaParameter javaParameter) {
                final String paramName = javaParameter.getName();
                DocParameter docParameter = new DocParameter();
                docParameter.setName(paramName);
                docParameter.setDocAnnotationList(MetadataDocUtil
                        .buildDocAnnotationList(javaParameter.getAnnotations()));
                docParameter.setType(javaParameter.getType().getFullyQualifiedName());
                // 基础类型和非基础类型
                // 初期版本可以做的比较简单，固定写死常见字段类型。
                // 8大基本类型+Number 类型。
                // 如何判断是否为用户自定义类型：让用户指定自己的包名称前缀。
                // 常规的判断方式，排除掉 jdk 自带的类型，其他全部为自定义类型。(推荐这种方式)
                if (!JavaClassUtil.isPrimitiveOrJdk(javaParameter.getType())) {
                    final List<JavaField> javaFieldList = JavaClassUtil
                            .getAllJavaFieldList(javaParameter.getType().getJavaClass());
                    docParameter.setDocFieldList(MetadataDocUtil.buildDocFieldList(javaFieldList));
                }
                DocletTag[] docletTags = javaMethod.getTagsByName(JavaTagConstant.PARAM);
                if (ArrayUtil.isNotEmpty(docletTags)) {
                    for (DocletTag docletTag : docletTags) {
                        String[] strings = docletTag.getParameters();
                        // 根据名称的指定信息做匹配
                        //1. 第一个参数是名称
                        //2. 第二个参数是值
                        if (ArrayUtil.isNotEmpty(strings)
                                && paramName.equalsIgnoreCase(strings[0])
                                && strings.length >= 2) {
                            docParameter.setComment(strings[1]);
                            break;
                        }
                    }
                }
                return docParameter;
            }
        });
    }

}
