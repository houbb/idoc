package com.github.houbb.idoc.api.model.metadata;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public class BaseDoc {

    /**
     * 行号
     */
    private int lineNum;

    /**
     * 作者
     */
    private String author;

    /**
     * 初始化版本
     */
    private String since;

    /**
     * 当前版本
     */
    private String version;

    /**
     * 名称
     */
    private String name;

    /**
     * 说明
     */
    private String desc;

    /**
     * 备注
     */
    private String remark;

    /**
     * 访问级别
     */
    private int modifiers;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }
}
