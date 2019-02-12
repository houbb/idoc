package com.github.houbb.idoc.api.model.metadata;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public final class DocField extends BaseDoc {

    /**
     * 字段类型
     */
    private String type;

    /**
     * 是否需要
     */
    private String required;

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

    @Override
    public String toString() {
        return "DocField{" +
                "type='" + type + '\'' +
                ", required='" + required + '\'' +
                "} " + super.toString();
    }
}
