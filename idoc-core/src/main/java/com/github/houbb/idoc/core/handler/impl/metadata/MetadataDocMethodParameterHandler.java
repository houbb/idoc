package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.config.DocConfig;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.metadata.DocMethodParameter;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.core.constant.JavaTagConstant;
import com.github.houbb.idoc.core.util.JavaClassUtil;
import com.github.houbb.idoc.core.util.JavaTypeAliasUtil;
import com.github.houbb.idoc.core.util.MetadataDocUtil;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 处理方法参数
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocMethodParameterHandler extends AbstractHandler<JavaMethod, List<DocMethodParameter>> {

    /**
     * 当前方法对应的类信息
     */
    private final DocClass docClass;

    /**
     * 配置信息
     */
    private final DocConfig docConfig;

    public MetadataDocMethodParameterHandler(DocClass docClass, DocConfig docConfig) {
        this.docClass = docClass;
        this.docConfig = docConfig;
    }

    @Override
    protected List<DocMethodParameter> doHandle(final JavaMethod javaMethod) {
        final JavaParameter[] javaParameters = javaMethod.getParameters();
        return ArrayUtil.buildList(javaParameters, new IHandler<JavaParameter, DocMethodParameter>() {
            @Override
            public DocMethodParameter handle(JavaParameter javaParameter) {
                final String paramName = javaParameter.getName();
                DocMethodParameter docMethodParameter = new DocMethodParameter();
                docMethodParameter.setName(paramName);
                docMethodParameter.setDocAnnotationList(MetadataDocUtil
                        .buildDocAnnotationList(javaParameter.getAnnotations()));
                final String type = javaParameter.getType().getFullyQualifiedName();
                final String typeAlias = JavaTypeAliasUtil.getAliasName(docConfig.getTypeAliases(), javaParameter.getType());
                docMethodParameter.setType(type);
                docMethodParameter.setTypeAlias(typeAlias);
                // 基础类型和非基础类型
                // 初期版本可以做的比较简单，固定写死常见字段类型。
                // 8大基本类型+Number 类型。
                // 如何判断是否为用户自定义类型：让用户指定自己的包名称前缀。
                // 常规的判断方式，排除掉 jdk 自带的类型，其他全部为自定义类型。(推荐这种方式)
                if (needEntryFieldHandle(javaParameter)) {
                    final List<JavaField> javaFieldList = JavaClassUtil
                            .getAllJavaFieldList(javaParameter.getType().getJavaClass());
                    docMethodParameter.setDocFieldList(CollectionUtil.buildList(javaFieldList, new MetadataDocFieldHandler(docConfig)));
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
                            docMethodParameter.setComment(strings[1]);
                            break;
                        }
                    }
                }
                return docMethodParameter;
            }
        });
    }

    /**
     * 是否需要吃力明细列表
     * @param javaParameter 参数
     * @return 是否需要
     */
    private boolean needEntryFieldHandle(final JavaParameter javaParameter) {
        if(JavaClassUtil.isPrimitiveOrJdk(javaParameter.getType())) {
            return false;
        }
        if(docClass.getFullName().equals(javaParameter.getType().getFullyQualifiedName())) {
            return false;
        }
        return true;
    }

}
