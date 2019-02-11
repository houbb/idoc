package com.github.houbb.idoc.core.util;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public final class ArrayUtil {

    /**
     * 是否为空
     * @param strings 数组
     * @return 是否为空
     */
    public static boolean isEmpty(String[] strings) {
        return null == strings
                || strings.length == 0;
    }

}
