package com.github.houbb.idoc.ftl.model;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class MarkdownDocField extends MarkdownDocBase {

    /**
     * 字段信息列表
     * 如果是一个特殊的集合类：则需要这个信息列表。
     * 如果是普通常量，则这个列表为空。
     */
    private List<MarkdownDocField> fieldEntryList;

    public List<MarkdownDocField> getFieldEntryList() {
        return fieldEntryList;
    }

    public void setFieldEntryList(List<MarkdownDocField> fieldEntryList) {
        this.fieldEntryList = fieldEntryList;
    }
}
