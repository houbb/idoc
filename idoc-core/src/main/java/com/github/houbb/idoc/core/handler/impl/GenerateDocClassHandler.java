package com.github.houbb.idoc.core.handler.impl;

import com.github.houbb.idoc.api.model.metadata.*;
import com.github.houbb.idoc.core.constant.JavaTagConstant;
import com.github.houbb.idoc.core.exception.IDocRuntimeException;
import com.github.houbb.idoc.core.handler.Handler;
import com.github.houbb.idoc.core.handler.JavaClassHandler;
import com.github.houbb.idoc.core.util.ArrayUtil;
import com.github.houbb.idoc.core.util.CollectionUtil;
import com.github.houbb.idoc.core.util.JavaClassUtil;
import com.github.houbb.idoc.core.util.ObjectUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.thoughtworks.qdox.model.*;

import java.util.List;

/**
 * 处理单个的类文件
 * TODO: since 等共有属性的获取
 * @author binbin.hou
 * date 2019/2/11
 */
public class GenerateDocClassHandler implements JavaClassHandler {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(GenerateDocClassHandler.class);

    @Override
    public DocClass handle(JavaClass javaClass) throws IDocRuntimeException {
        return buildDocClass(javaClass);
    }


    /**
     * 构建 docClass 信息
     * @param javaClass java class 信息
     * @return docClass 信息
     */
    private DocClass buildDocClass(final JavaClass javaClass) {
        if(ObjectUtil.isNull(javaClass)) {
            return null;
        }

        // 原始基础信息
        DocClass docClass = new DocClass();
        docClass.setName(javaClass.getName());
        docClass.setPackageName(javaClass.getPackageName());
        docClass.setFullName(javaClass.getFullyQualifiedName());

        // java 默认的字段
        if(isPrimitiveOrJdk(javaClass.asType())) {
            return docClass;
        }

        // 类基础信息
        docClass.setModifiers(javaClass.getModifiers());
        docClass.setComment(javaClass.getComment());

        // 类-标签信息
        final DocletTag[] docletTagArray = javaClass.getTags();
        docClass.setDocTagList(buildDocTagList(docletTagArray));

        // 类-注解信息
        final Annotation[] annotations = javaClass.getAnnotations();
        docClass.setDocAnnotationList(buildDocAnnotationList(annotations));

        // 字段信息
        final List<JavaField> javaFieldList = JavaClassUtil.getAllJavaFieldList(javaClass);
        docClass.setDocFieldList(buildDocFieldList(javaFieldList));

        // 方法信息
        final JavaMethod[] javaMethods = javaClass.getMethods();
        docClass.setDocMethodList(buildDocMethodList(javaMethods));

        return docClass;
    }

    /**
     * 构建标签信息
     * @param docletTagArray 原始信息
     * @return 构建后的信息
     */
    private List<DocTag> buildDocTagList(final DocletTag[] docletTagArray) {
        return ArrayUtil.buildList(docletTagArray, new Handler<DocletTag, DocTag>() {
            @Override
            public DocTag handle(DocletTag docletTag) throws IDocRuntimeException {
                DocTag docTag = new DocTag();
                docTag.setName(docletTag.getName());
                docTag.setLineNum(docletTag.getLineNumber());
                docTag.setParameters(docletTag.getParameters());
                docTag.setValue(docletTag.getValue());
                return docTag;
            }
        });
    }

    /**
     * 构建类注解信息
     * TODO：不重要
     * @param annotations 注解
     * @return 构建结果
     */
    private List<DocAnnotation> buildDocAnnotationList(final Annotation[] annotations) {
        return ArrayUtil.buildList(annotations, new Handler<Annotation, DocAnnotation>() {
            @Override
            public DocAnnotation handle(Annotation annotation) throws IDocRuntimeException {
                return null;
            }
        });
    }


    /**
     * 构建文档字段信息列表
     * @param javaFieldList 文档字段列表
     * @return 列表结果
     */
    private List<DocField> buildDocFieldList(final List<JavaField> javaFieldList) {
        return CollectionUtil.buildList(javaFieldList, new Handler<JavaField, DocField>(){
            @Override
            public DocField handle(JavaField javaField) throws IDocRuntimeException {
                DocField docField = new DocField();
                docField.setName(javaField.getName());
                docField.setType(javaField.getType().getFullyQualifiedName());
                docField.setComment(javaField.getComment());
                // 使用 doclet，缺点：严格的 java-doc 会报错
                // 使用判断的方式，会导致处理其他特别麻烦。
                DocletTag requireTag = javaField.getTagByName(JavaTagConstant.IDOC_REQUIRE);
                DocletTag remarkTag = javaField.getTagByName(JavaTagConstant.IDOC_REMARK);

                if(ObjectUtil.isNotNull(requireTag)) {
                    docField.setRequired(requireTag.getValue());
                }
                if(ObjectUtil.isNotNull(remarkTag)) {
                    docField.setRemark(remarkTag.getValue());
                }
                return docField;
            }
        });
    }

    /**
     * 构建方法列表
     * @param javaMethods 原始方法
     * @return 构建结果
     */
    private List<DocMethod> buildDocMethodList(final JavaMethod[] javaMethods) {
        return ArrayUtil.buildList(javaMethods, new Handler<JavaMethod, DocMethod>(){
            @Override
            public DocMethod handle(JavaMethod javaMethod) throws IDocRuntimeException {
                DocMethod docMethod = new DocMethod();
                docMethod.setName(javaMethod.getName());
                docMethod.setComment(javaMethod.getComment());
                docMethod.setSignature(javaMethod.getCallSignature());
                docMethod.setModifiers(javaMethod.getModifiers());
                DocletTag docletTag = javaMethod.getTagByName(JavaTagConstant.SINCE);
                if(ObjectUtil.isNotNull(docletTag)) {
                    docMethod.setSince(docletTag.getValue());
                }

                // 返回类型
                docMethod.setDocReturnClass(buildDocReturnClass(javaMethod));

                // tags 信息
                DocletTag[] docletTags = javaMethod.getTags();
                List<DocTag> docTagList = buildDocTagList(docletTags);
                docMethod.setDocTagList(docTagList);

                // 参数信息
                JavaParameter[] javaParameters = javaMethod.getParameters();
                docMethod.setDocParameterList(buildDocParameterList(javaParameters, javaMethod));
                return docMethod;
            }
        });
    }

    /**
     * 构建返回类型
     * @param javaMethod java 方法
     * @return 结果
     */
    private DocClass buildDocReturnClass(final JavaMethod javaMethod) {
        JavaClass returnClass = javaMethod.getReturnType()==null ? null : javaMethod.getReturnType().getJavaClass();
        DocClass docClass = buildDocClass(returnClass);

        // 注释
        DocletTag docletTag = javaMethod.getTagByName(JavaTagConstant.RETURN);
        if(ObjectUtil.isNotNull(docletTag)) {
            docClass.setMethodComment(docletTag.getValue());
        }
        return docClass;
    }

    /**
     * 构建参数
     * TODO: 对于集合类型的考虑 Array Collection Map
     * @param javaParameters 方法参数
     * @param javaMethod 方法
     * @return 结果信息
     */
    private List<DocParameter> buildDocParameterList(final JavaParameter[] javaParameters, final JavaMethod javaMethod) {
        return ArrayUtil.buildList(javaParameters, new Handler<JavaParameter, DocParameter>() {
            @Override
            public DocParameter handle(JavaParameter javaParameter) throws IDocRuntimeException {
                final String paramName = javaParameter.getName();
                DocParameter docParameter = new DocParameter();
                docParameter.setName(paramName);
                docParameter.setDocAnnotationList(buildDocAnnotationList(javaParameter.getAnnotations()));
                docParameter.setType(javaParameter.getType().getFullyQualifiedName());
                // 基础类型和非基础类型
                // 初期版本可以做的比较简单，固定写死常见字段类型。
                // 8大基本类型+Number 类型。
                // 如何判断是否为用户自定义类型：让用户指定自己的包名称前缀。
                // 常规的判断方式，排除掉 jdk 自带的类型，其他全部为自定义类型。(推荐这种方式)
                if(!isPrimitiveOrJdk(javaParameter.getType())) {
                    final List<JavaField> javaFieldList = JavaClassUtil
                            .getAllJavaFieldList(javaParameter.getType().getJavaClass());
                    docParameter.setDocFieldList(buildDocFieldList(javaFieldList));
                }
                DocletTag[] docletTags = javaMethod.getTagsByName(JavaTagConstant.PARAM);
                if(ArrayUtil.isNotEmpty(docletTags)) {
                    for(DocletTag docletTag : docletTags) {
                        String[] strings = docletTag.getParameters();
                        // 根据名称的指定信息做匹配
                        //1. 第一个参数是名称
                        //2. 第二个参数是值
                        if(ArrayUtil.isNotEmpty(strings)
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


    /**
     * 是否为 jdk 默认的对象类型
     * @param type 类型
     * @return 是否
     */
    private boolean isPrimitiveOrJdk(final Type type) {
        return type.isPrimitive()
                || type.getFullyQualifiedName().startsWith("java.");
    }
}
