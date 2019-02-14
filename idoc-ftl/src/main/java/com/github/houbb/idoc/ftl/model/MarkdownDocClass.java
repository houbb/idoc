package com.github.houbb.idoc.ftl.model;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class MarkdownDocClass extends MarkdownDocBase {

    /**
     * 方法列表
     */
    private List<MarkdownDocMethod> methodList;

    public List<MarkdownDocMethod> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<MarkdownDocMethod> methodList) {
        this.methodList = methodList;
    }

}
