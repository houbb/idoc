/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.core.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * java 类型别名工具类
 * @author binbin.hou
 * @since 0.1.0
 */
public final class JavaTypeAliasUtil {

    private static final Map<String, String> TYPE_ALIAS_MAP = new HashMap<>();

    static {
        TYPE_ALIAS_MAP.put("java.lang.String", "字符串");
        TYPE_ALIAS_MAP.put("java.lang.Boolean", "布尔值");
        TYPE_ALIAS_MAP.put("java.lang.Byte", "字节");
        TYPE_ALIAS_MAP.put("java.lang.Character", "字符");
        TYPE_ALIAS_MAP.put("java.lang.Double", "浮点型");
        TYPE_ALIAS_MAP.put("java.lang.Float", "浮点型");
        TYPE_ALIAS_MAP.put("java.lang.Integer", "整型");
        TYPE_ALIAS_MAP.put("java.lang.Long", "长整型");
        TYPE_ALIAS_MAP.put("java.lang.Short", "短整型");
        TYPE_ALIAS_MAP.put("java.lang.BigDecimal", "数字");
        TYPE_ALIAS_MAP.put("java.lang.BigInteger", "数字");
        TYPE_ALIAS_MAP.put("java.lang.LocalDate", "日期");
        TYPE_ALIAS_MAP.put("java.lang.LocalDateTime", "日期时间");
        TYPE_ALIAS_MAP.put("java.lang.LocalTime", "时间");
        TYPE_ALIAS_MAP.put("java.lang.Date", "日期");
        TYPE_ALIAS_MAP.put("java.lang.Currency", "货币");
    }

    /**
     * 获取名称别名
     * @return map 信息
     */
    public static Map<String, String> getTypeAliasMap() {
        return Collections.unmodifiableMap(TYPE_ALIAS_MAP);
    }

}
