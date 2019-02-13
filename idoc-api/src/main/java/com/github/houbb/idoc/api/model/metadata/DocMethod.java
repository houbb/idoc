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
    private List<DocParameter> docParameterList;

    /**
     * 返回对象
     */
    private DocClass docReturnClass;

    //TODO 异常信息

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

    public List<DocParameter> getDocParameterList() {
        return docParameterList;
    }

    public void setDocParameterList(List<DocParameter> docParameterList) {
        this.docParameterList = docParameterList;
    }

    public DocClass getDocReturnClass() {
        return docReturnClass;
    }

    public void setDocReturnClass(DocClass docReturnClass) {
        this.docReturnClass = docReturnClass;
    }
}
