package com.github.houbb.idoc.common.model;

import java.util.List;
import java.util.Map;

/**
 * 简化方法信息
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

    /**
     * 存放参数详情
     */
    private Map<String, List<SimplifyDocField>> paramDetails;

    /**
     * 存放返回详情列表
     */
    private Map<String, List<SimplifyDocField>> returnDetails;

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

    public Map<String, List<SimplifyDocField>> getParamDetails() {
        return paramDetails;
    }

    public void setParamDetails(Map<String, List<SimplifyDocField>> paramDetails) {
        this.paramDetails = paramDetails;
    }

    public Map<String, List<SimplifyDocField>> getReturnDetails() {
        return returnDetails;
    }

    public void setReturnDetails(Map<String, List<SimplifyDocField>> returnDetails) {
        this.returnDetails = returnDetails;
    }
}
