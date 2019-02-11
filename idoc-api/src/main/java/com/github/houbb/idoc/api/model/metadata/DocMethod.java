package com.github.houbb.idoc.api.model.metadata;

import java.util.List;

/**
 * @author binbin.hou
 * date 2019/2/11
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
    private List<DocClass> docParameterList;

    /**
     * 返回对象
     */
    private DocClass returnClass;

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

    public List<DocClass> getDocParameterList() {
        return docParameterList;
    }

    public void setDocParameterList(List<DocClass> docParameterList) {
        this.docParameterList = docParameterList;
    }

    public DocClass getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(DocClass returnClass) {
        this.returnClass = returnClass;
    }
}
