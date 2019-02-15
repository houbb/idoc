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
    private List<MarkdownDocField> params;

    /**
     * 方法出参
     */
    private List<MarkdownDocField> returns;

    public List<MarkdownDocField> getParams() {
        return params;
    }

    public void setParams(List<MarkdownDocField> params) {
        this.params = params;
    }

    public List<MarkdownDocField> getReturns() {
        return returns;
    }

    public void setReturns(List<MarkdownDocField> returns) {
        this.returns = returns;
    }
}
