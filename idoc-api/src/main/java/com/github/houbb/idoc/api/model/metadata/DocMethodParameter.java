package com.github.houbb.idoc.api.model.metadata;

import java.util.List;

/**
 * 方法参数信息
 * @author binbin.hou
 * @since 0.0.1
 */
public final class DocMethodParameter extends BaseDoc {

    /**
     * 参数类型
     */
    private String type;

    /**
     * 参数类型别名
     * @since 0.1.0
     */
    private String typeAlias;

    /**
     * 当前入参下面的字段信息
     */
    private List<DocField> docFieldList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DocField> getDocFieldList() {
        return docFieldList;
    }

    public void setDocFieldList(List<DocField> docFieldList) {
        this.docFieldList = docFieldList;
    }

    public String getTypeAlias() {
        return typeAlias;
    }

    public void setTypeAlias(String typeAlias) {
        this.typeAlias = typeAlias;
    }
}
