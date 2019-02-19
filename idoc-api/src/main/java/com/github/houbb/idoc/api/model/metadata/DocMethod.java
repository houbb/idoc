package com.github.houbb.idoc.api.model.metadata;

import java.util.List;

/**
 * 方法信息
 * @author binbin.hou
 * @since 0.0.1
 */
public final class DocMethod extends BaseDoc {

    /**
     * 签名
     */
    private String signature;

    /**
     * 源码
     */
    private String sourceCode;

    /**
     * 参数列表
     */
    private List<DocMethodParameter> docMethodParameterList;

    /**
     * 方法返回对象
     */
    private DocMethodReturn docMethodReturn;

    //TODO 异常信息 + @see 列表信息

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public List<DocMethodParameter> getDocMethodParameterList() {
        return docMethodParameterList;
    }

    public void setDocMethodParameterList(List<DocMethodParameter> docMethodParameterList) {
        this.docMethodParameterList = docMethodParameterList;
    }

    public DocMethodReturn getDocMethodReturn() {
        return docMethodReturn;
    }

    public void setDocMethodReturn(DocMethodReturn docMethodReturn) {
        this.docMethodReturn = docMethodReturn;
    }

}
