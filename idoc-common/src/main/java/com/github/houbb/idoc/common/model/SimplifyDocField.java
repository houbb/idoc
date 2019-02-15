package com.github.houbb.idoc.common.model;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyDocField extends SimplifyDocBase {

    /**
     * 字段类型
     * TODO：字段显示类型映射处理
     * 支持用户自定义
     */
    private String type;

    /**
     * 是否必填
     */
    private String required;

    /**
     * 字段信息列表
     * 如果是一个特殊的集合类：则需要这个信息列表。
     * 如果是普通常量，则这个列表为空。
     */
    private List<SimplifyDocField> entries;

    public List<SimplifyDocField> getEntries() {
        return entries;
    }

    public void setEntries(List<SimplifyDocField> entries) {
        this.entries = entries;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}
