package com.github.houbb.idoc.core.util;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public final class ObjectUtil {

    /**
     * 是否为空
     * @param object 对象
     * @return 是否为空
     */
    public static boolean isNull(final Object object) {
        return null == object;
    }

    /**
     * 是否为空
     * @param object 对象
     * @return 是否为空
     */
    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }

}
