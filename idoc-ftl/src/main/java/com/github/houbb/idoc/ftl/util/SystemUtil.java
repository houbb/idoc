/*
 * Copyright (c)  2018. houbinbin Inc.
 * gen-maven-plugin All rights reserved.
 */

package com.github.houbb.idoc.ftl.util;

/**
 * 系统工具类
 * @author bbhou
 * @version 1.0.0
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
