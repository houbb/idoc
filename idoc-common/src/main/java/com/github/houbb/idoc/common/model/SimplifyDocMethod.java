package com.github.houbb.idoc.common.model;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyDocMethod extends SimplifyDocBase {

    /**
     * 方法入参
     */
    private List<SimplifyDocField> params;

    /**
     * 方法出参
     */
    private List<SimplifyDocField> returns;

    public List<SimplifyDocField> getParams() {
        return params;
    }

    public void setParams(List<SimplifyDocField> params) {
        this.params = params;
    }

    public List<SimplifyDocField> getReturns() {
        return returns;
    }

    public void setReturns(List<SimplifyDocField> returns) {
        this.returns = returns;
    }
}
