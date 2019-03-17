package com.github.houbb.idoc.core.util;

import com.thoughtworks.qdox.model.Type;
import org.apache.commons.lang3.StringUtils;

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
        TYPE_ALIAS_MAP.put("java.util.Map", "映射");

        /**
         * 针对数组类型
         */
        TYPE_ALIAS_MAP.put("array", "数组");
        /**
         * 空类型
         */
        TYPE_ALIAS_MAP.put("void", "空");
    }

    /**
     * 获取类型别名集合
     * @return 集合
     */
    public static Map<String, String> getTypeAliasMap() {
        return Collections.unmodifiableMap(TYPE_ALIAS_MAP);
    }

    /**
     * 获取别名
     * 1. 如果对应的别名不存在，则使用原始的 {@link Type#getFullyQualifiedName()}
     * 2. 如果是数组，全称获取到的是其中的类型。这个需要注意一下。
     * @param aliasMap 别名集合
     * @param type 类型
     * @return 别名
     */
    public static String getAliasName(final Map<String, String> aliasMap, final Type type) {
        if(type.isArray()) {
            // 数组类型
            return aliasMap.get("array");
        } else if(type.isVoid()) {
            // 空类型
            return aliasMap.get("void");
        } else {
            final String fullName = type.getFullyQualifiedName();
            String alias = aliasMap.get(fullName);
            if(StringUtils.isNotEmpty(alias)) {
                return alias;
            }
            return fullName;
        }
    }

}
