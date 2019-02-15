/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.ftl.convetor;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.metadata.DocMethod;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.ftl.model.MarkdownDocClass;
import com.github.houbb.paradise.common.util.StringUtil;

import java.util.List;

/**
 * <p> markdown class 转换器 </p>
 *
 * <pre> Created: 2019/2/15 7:34 AM  </pre>
 * <pre> Project: idoc  </pre>
 *
 * @author houbinbin
 */
public class MarkdownClassConvetor implements IHandler<DocClass, MarkdownDocClass> {

    @Override
    public MarkdownDocClass handle(DocClass docClass) {
        if (null == docClass) {
            return null;
        }

        // 基础信息


        // 方法基本信息

        // 方法入参

        // 方法出参
        return null;
    }

    /**
     * 构建 class 信息
     * @param docClass 原始 class 信息
     * @return 结果
     */
    private MarkdownDocClass buildMarkdownDocClass(final DocClass docClass) {
        MarkdownDocClass markdownDocClass = new MarkdownDocClass();
        markdownDocClass.setName(docClass.getName());
        markdownDocClass.setComment(docClass.getComment());
        String remark = getRemark(docClass.getComment(), docClass.getRemark());
        markdownDocClass.setRemark(remark);

        // 方法信息
        List<DocMethod> docMethodList = docClass.getDocMethodList();

        return markdownDocClass;
    }

    /**
     * 获取对应的备注信息
     * 1. 因为会出现经常不写备注的场景。
     * @param comment 基础说明
     * @param remark 备注信息
     * @return 结果
     */
    private String getRemark(final String comment, final String remark) {
        if(StringUtil.isNotEmpty(remark)) {
            return remark;
        }

        return comment;
    }

}
