package com.github.houbb.idoc.common.model;

/**
 * 返回字段信息
 *
 * @author binbin.hou
 * @since 0.4.0
 */
public class SimplifyDocReturn extends SimplifyDocBase {

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
     * 全称
     * @since 0.4.0
     */
    private String fullName;

    /**
     * 包名称
     * @since 0.4.0
     */
    private String packageName;

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

    public String getTypeAlias() {
        return typeAlias;
    }

    public void setTypeAlias(String typeAlias) {
        this.typeAlias = typeAlias;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
