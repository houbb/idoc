package com.github.houbb.idoc.common.model;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyDocBase {

    /**
     * 名称
     */
    private String name;

    /**
     * 注释
     */
    private String comment;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注第一行
     * @since 0.2.0
     */
    private String commentFirstLine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCommentFirstLine() {
        return commentFirstLine;
    }

    public void setCommentFirstLine(String commentFirstLine) {
        this.commentFirstLine = commentFirstLine;
    }
}
