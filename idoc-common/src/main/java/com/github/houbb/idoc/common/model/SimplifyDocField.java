package com.github.houbb.idoc.common.model;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyDocField extends SimplifyDocBase {

    /**
     * 字段类型
     * 支持用户自定义
     */
    private String type;

    /**
     * 是否必填
     */
    private String require;

    /**
     * 字段类型别名
     * @since 0.1.0
     */
    private String typeAlias;

    /**
     * 详情列表信息
     */
    private List<SimplifyDocField> entries;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public List<SimplifyDocField> getEntries() {
        return entries;
    }

    public void setEntries(List<SimplifyDocField> entries) {
        this.entries = entries;
    }

    public String getTypeAlias() {
        return typeAlias;
    }

    public void setTypeAlias(String typeAlias) {
        this.typeAlias = typeAlias;
    }
}
