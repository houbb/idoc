package com.github.houbb.idoc.api.model.metadata;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public class BaseDoc {

    /**
     * 开始版本号
     * 备注：用户可以指定版本号，过滤生成文档信息
     */
    private String since;

    /**
     * 名称
     */
    private String name;

    /**
     * 注释
     */
    private String comment;

    /**
     * 备注
     */
    private String remark;

    /**
     * 访问级别
     */
    private String[] modifiers;

    /**
     * doc 标签列表
     */
    private List<DocTag> docTagList;

    /**
     * doc 注解列表
     */
    private List<DocAnnotation> docAnnotationList;

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getModifiers() {
        return modifiers;
    }

    public void setModifiers(String[] modifiers) {
        this.modifiers = modifiers;
    }

    public List<DocTag> getDocTagList() {
        return docTagList;
    }

    public void setDocTagList(List<DocTag> docTagList) {
        this.docTagList = docTagList;
    }

    public List<DocAnnotation> getDocAnnotationList() {
        return docAnnotationList;
    }

    public void setDocAnnotationList(List<DocAnnotation> docAnnotationList) {
        this.docAnnotationList = docAnnotationList;
    }

    @Override
    public String toString() {
        return "BaseDoc{" +
                "since='" + since + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", remark='" + remark + '\'' +
                ", modifiers=" + Arrays.toString(modifiers) +
                ", docTagList=" + docTagList +
                ", docAnnotationList=" + docAnnotationList +
                '}';
    }
}
