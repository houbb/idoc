package com.github.houbb.idoc.api.model.metadata;

import java.util.List;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public class DocClass extends BaseDoc {

    /**
     * 全称
     */
    private String fullName;

    /**
     * 当作为方法的参数/返回值的时候
     */
    private String methodComment;

    /**
     * 包名称
     */
    private String packageName;

    /**
     * 方法列表
     */
    private List<DocMethod> docMethodList;

    /**
     * 字段列表
     */
    private List<DocField> docFieldList;

    /**
     * 内嵌类列表
     */
    private List<DocClass> nestClassList;

    /**
     * 实现接口列表
     */
    private List<DocClass> interfaceClassList;

    /**
     * 父类
     */
    private DocClass parentClass;

    public String getMethodComment() {
        return methodComment;
    }

    public void setMethodComment(String methodComment) {
        this.methodComment = methodComment;
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

    public List<DocMethod> getDocMethodList() {
        return docMethodList;
    }

    public void setDocMethodList(List<DocMethod> docMethodList) {
        this.docMethodList = docMethodList;
    }

    public List<DocField> getDocFieldList() {
        return docFieldList;
    }

    public void setDocFieldList(List<DocField> docFieldList) {
        this.docFieldList = docFieldList;
    }

    public List<DocClass> getNestClassList() {
        return nestClassList;
    }

    public void setNestClassList(List<DocClass> nestClassList) {
        this.nestClassList = nestClassList;
    }

    public List<DocClass> getInterfaceClassList() {
        return interfaceClassList;
    }

    public void setInterfaceClassList(List<DocClass> interfaceClassList) {
        this.interfaceClassList = interfaceClassList;
    }

    public DocClass getParentClass() {
        return parentClass;
    }

    public void setParentClass(DocClass parentClass) {
        this.parentClass = parentClass;
    }

    @Override
    public String toString() {
        return "DocClass{" +
                "fullName='" + fullName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", docMethodList=" + docMethodList +
                ", docFieldList=" + docFieldList +
                ", nestClassList=" + nestClassList +
                ", interfaceClassList=" + interfaceClassList +
                ", parentClass=" + parentClass +
                "} " + super.toString();
    }
}
