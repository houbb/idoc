package com.github.houbb.idoc.common.util;

import com.github.houbb.heaven.util.lang.StringUtil;

/**
 * 备注工具类
 *
 * @author binbin.hou
 * @since 0.2.0
 */
public final class CommentUtil {

    private CommentUtil(){}

    /**
     * 获取备注的第一行内容
     * @param comment 备注
     * @return 结果
     * @since 0.0.2
     */
    public static String getFirstLine(String comment) {
        if(StringUtil.isEmptyTrim(comment)) {
            return null;
        }

        // 换行
        String[] strings = comment.split("\n");
        String line = strings[0];
        return line.replaceAll("<p>","")
                .replaceAll("</p>", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
    }

}
