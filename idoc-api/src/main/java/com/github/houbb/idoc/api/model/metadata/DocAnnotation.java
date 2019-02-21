package com.github.houbb.idoc.api.model.metadata;

import java.util.Map;

/**
 * 注解信息
 * @author binbin.hou
 * @since 0.0.1
 */
public final class DocAnnotation extends BaseDoc {

    /**
     * 属性配置
     * @since 0.0.2
     */
    private Map<String, String> properties;

    /**
     * 名称参数信息
     * @since 0.0.2
     */
    private Map<String, Object> namedParameters;

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, Object> getNamedParameters() {
        return namedParameters;
    }

    public void setNamedParameters(Map<String, Object> namedParameters) {
        this.namedParameters = namedParameters;
    }

}
