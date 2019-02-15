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
    private List<MarkdownDocMethod> methods;

    public List<MarkdownDocMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<MarkdownDocMethod> methods) {
        this.methods = methods;
    }

}
