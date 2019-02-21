package com.github.houbb.idoc.api.model.metadata;

import java.util.Arrays;
import java.util.List;

/**
 * 基础信息
 * @author binbin.hou
 * @since 0.0.1
 */
public class BaseDoc {

    /**
     * 开始版本号
     * 备注：用户可以指定版本号，过滤生成文档信息
     */
    private String since;

    /**
     * 版本信息
     */
    private String version;

    /**
     * 作者列表
     */
    private List<String> authorList;

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

    /**
     * 参见列表
     */
    private List<DocClass> seeList;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public List<DocClass> getSeeList() {
        return seeList;
    }

    public void setSeeList(List<DocClass> seeList) {
        this.seeList = seeList;
    }

}
