package com.github.houbb.idoc.api.model.config;

import java.util.Arrays;
import java.util.Map;

/**
 * 文档生成配置信息
 * @author binbin.hou
 * @since 0.0.1
 */
public class DocConfig {

    /**
     * 是否覆盖编写
     */
    private boolean isOverwriteWhenExists;

    /**
     * 所有的内容生成一个文件
     */
    private boolean isAllInOne;

    /**
     * 编码
     */
    private String encoding;

    /**
     * 生成器列表
     */
    private String[] generates;

    /**
     * 过滤器列表
     */
    private String[] generateFilters;

    /**
     * 类型别称
     * @since 0.1.0
     */
    private Map<String, String> typeAliases;

    public boolean isOverwriteWhenExists() {
        return isOverwriteWhenExists;
    }

    public void setOverwriteWhenExists(boolean overwriteWhenExists) {
        isOverwriteWhenExists = overwriteWhenExists;
    }

    public boolean isAllInOne() {
        return isAllInOne;
    }

    public void setAllInOne(boolean allInOne) {
        isAllInOne = allInOne;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String[] getGenerates() {
        return generates;
    }

    public void setGenerates(String[] generates) {
        this.generates = generates;
    }

    public String[] getGenerateFilters() {
        return generateFilters;
    }

    public void setGenerateFilters(String[] generateFilters) {
        this.generateFilters = generateFilters;
    }

    public Map<String, String> getTypeAliases() {
        return typeAliases;
    }

    public void setTypeAliases(Map<String, String> typeAliases) {
        this.typeAliases = typeAliases;
    }

    @Override
    public String toString() {
        return "DocConfig{" +
                "isOverwriteWhenExists=" + isOverwriteWhenExists +
                ", isAllInOne=" + isAllInOne +
                ", encoding='" + encoding + '\'' +
                ", generates=" + Arrays.toString(generates) +
                ", generateFilters=" + Arrays.toString(generateFilters) +
                ", typeAliases=" + typeAliases +
                '}';
    }
}
