package com.github.houbb.idoc.core.util;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Java 类型别称工具类
 * @author bbhou
 * @since 0.1.0
 */
public final class JavaTypeAliasUtil {

    /**
     * 类型别称
     */
    private static final Map<String, String> TYPE_ALIAS_MAP = new ConcurrentHashMap<>();

    /**
     *  java类util
     */
    private JavaTypeAliasUtil(){}


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

        TYPE_ALIAS_MAP.put("boolean", "布尔值");
        TYPE_ALIAS_MAP.put("byte", "字节");
        TYPE_ALIAS_MAP.put("char", "字符");
        TYPE_ALIAS_MAP.put("double", "浮点型");
        TYPE_ALIAS_MAP.put("float", "浮点型");
        TYPE_ALIAS_MAP.put("int", "整型");
        TYPE_ALIAS_MAP.put("long", "长整型");
        TYPE_ALIAS_MAP.put("short", "短整型");

        TYPE_ALIAS_MAP.put("java.math.BigDecimal", "数字");
        TYPE_ALIAS_MAP.put("java.math.BigInteger", "数字");
        TYPE_ALIAS_MAP.put("java.time.LocalDate", "日期");
        TYPE_ALIAS_MAP.put("java.time.LocalDateTime", "日期时间");
        TYPE_ALIAS_MAP.put("java.time.LocalTime", "时间");
        TYPE_ALIAS_MAP.put("java.util.Date", "日期");
        TYPE_ALIAS_MAP.put("java.util.Currency", "货币");

        TYPE_ALIAS_MAP.put("java.util.Collection", "集合");
        TYPE_ALIAS_MAP.put("java.util.List", "列表");
    }

    /**
     * 获取类型别名集合
     * @return 集合
     */
    public static Map<String, String> getTypeAliasMap() {
        return Collections.unmodifiableMap(TYPE_ALIAS_MAP);
    }

}
