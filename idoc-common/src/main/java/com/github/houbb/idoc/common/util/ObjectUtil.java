/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.common.util;

/**
 * @author binbin.hou
 * @since 0.0.1
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
