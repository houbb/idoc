package com.github.houbb.idoc.common.util;

import com.github.houbb.paradise.common.util.StringUtil;

/**
 * doc 类工具类
 * @author binbin.hou
 * @since 0.0.1
 */
public class DocClassUtil {

    /**
     * 获取对应的备注信息
     * 1. 因为会出现经常不写备注的场景。
     * 直接将 remark 在取得时候就设置为 comment，避免这一步操作。
     * @param comment 基础说明
     * @param remark 备注信息
     * @return 结果
     */
    public static String getRemark(final String comment, final String remark) {
        if(StringUtil.isNotEmpty(remark)) {
            return remark;
        }

        return comment;
    }

}
