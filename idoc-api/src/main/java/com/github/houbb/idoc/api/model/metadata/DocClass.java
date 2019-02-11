package com.github.houbb.idoc.api.model.metadata;

import java.util.List;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public final class DocClass extends BaseDoc{

    /**
     * 全称
     */
    private String fullName;

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
