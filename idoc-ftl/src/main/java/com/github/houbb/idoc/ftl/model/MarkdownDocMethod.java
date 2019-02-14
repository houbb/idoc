package com.github.houbb.idoc.ftl.model;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class MarkdownDocMethod extends MarkdownDocBase {

    /**
     * 方法入参
     */
    private List<MarkdownDocField> paramFieldList;

    /**
     * 方法出参
     */
    private List<MarkdownDocField> returnFieldList;

    public List<MarkdownDocField> getParamFieldList() {
        return paramFieldList;
    }

    public void setParamFieldList(List<MarkdownDocField> paramFieldList) {
        this.paramFieldList = paramFieldList;
    }

    public List<MarkdownDocField> getReturnFieldList() {
        return returnFieldList;
    }

    public void setReturnFieldList(List<MarkdownDocField> returnFieldList) {
        this.returnFieldList = returnFieldList;
    }
}
