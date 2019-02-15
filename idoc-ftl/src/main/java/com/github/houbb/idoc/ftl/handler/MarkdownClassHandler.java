///*
// * Copyright (c)  2019. houbinbin Inc.
// * idoc All rights reserved.
// */
//
//package com.github.houbb.idoc.ftl.handler;
//
//import com.github.houbb.idoc.api.model.metadata.DocClass;
//import com.github.houbb.idoc.api.model.metadata.DocField;
//import com.github.houbb.idoc.api.model.metadata.DocMethod;
//import com.github.houbb.idoc.api.model.metadata.DocParameter;
//import com.github.houbb.idoc.simplify.handler.IHandler;
//import com.github.houbb.idoc.simplify.util.CollectionUtil;
//import com.github.houbb.idoc.ftl.model.MarkdownDocClass;
//import com.github.houbb.idoc.ftl.model.MarkdownDocField;
//import com.github.houbb.idoc.ftl.model.MarkdownDocMethod;
//import com.github.houbb.paradise.simplify.util.StringUtil;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * <p> markdown class 转换器 </p>
// *
// * <pre> Created: 2019/2/15 7:34 AM  </pre>
// * <pre> Project: idoc  </pre>
// *
// * @author houbinbin
// */
//@Deprecated
//public class MarkdownClassHandler implements IHandler<DocClass, MarkdownDocClass> {
//
//    @Override
//    public MarkdownDocClass handle(DocClass docClass) {
//        MarkdownDocClass markdownDocClass = new MarkdownDocClass();
//        markdownDocClass.setName(docClass.getName());
//        markdownDocClass.setComment(docClass.getComment());
//        String remark = getRemark(docClass.getComment(), docClass.getRemark());
//        markdownDocClass.setRemark(remark);
//
//        // 方法信息
//        List<DocMethod> docMethodList = docClass.getDocMethodList();
//        return markdownDocClass;
//    }
//
//    /**
//     * 构建方法
//     * @param docMethod 方法
//     * @return 结果
//     */
//    private MarkdownDocMethod buildMarkdownDocMethod(final DocMethod docMethod) {
//        if(null == docMethod) {
//            return null;
//        }
//        MarkdownDocMethod markdownDocMethod = new MarkdownDocMethod();
//        markdownDocMethod.setComment(docMethod.getComment());
//        markdownDocMethod.setRemark(getRemark(docMethod.getComment(), docMethod.getRemark()));
//        markdownDocMethod.setName(docMethod.getName());
//
//        // 设置入参
//        List<MarkdownDocField> params = buildParams(docMethod.getDocParameterList());
//
//        // 设置出参
//
//        return markdownDocMethod;
//    }
//
//    /**
//     * 构建入参信息
//     * @param docParameterList 原始参数列表
//     * @return 构建后的参数结果
//     */
//    private List<MarkdownDocField> buildParams(final List<DocParameter> docParameterList) {
//        return CollectionUtil.buildList(docParameterList, new IHandler<DocParameter, MarkdownDocField>() {
//            @Override
//            public MarkdownDocField handle(DocParameter docParameter) {
//                return null;
//            }
//        });
//    }
//
//    /**
//     * 构建返回值结果
//     * @param returnDocClass 返回结果
//     * @return 构建后的参数列表
//     */
//    private List<MarkdownDocField> buildRuturns(final DocClass returnDocClass) {
//        if(null == returnDocClass) {
//            return Collections.emptyList();
//        }
//
//        final List<DocField> docFieldList = returnDocClass.getDocFieldList();
//        ;
//    }
//
//
//    /**
//     * 获取对应的备注信息
//     * 1. 因为会出现经常不写备注的场景。
//     * @param comment 基础说明
//     * @param remark 备注信息
//     * @return 结果
//     */
//    private String getRemark(final String comment, final String remark) {
//        if(StringUtil.isNotEmpty(remark)) {
//            return remark;
//        }
//
//        return comment;
//    }
//
//}
