/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.common.util;

/**
 * 系统工具类
 * @author bbhou
 * @since 0.0.1
 */
public final class SystemUtil {

    /**    
     *  系统有用    
     */    
    private SystemUtil(){}

    /**
     * 获取当前用户名
     * @return 当前用户名
     */
    public static String getCurrentUserName() {
        return System.getProperty("user.name");
    }

}
