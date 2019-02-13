package com.github.houbb.idoc.api.model.metadata;

import java.util.Arrays;

/**
 * doclet 标签值对象
 * @author houbinbin
 * @since 0.0.1
 */
public class DocTag {

    /**
     * 行号
     */
    private int lineNum;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 参数
     */
    private String[] parameters;

    /**    
     * 得到行号    
     *    
     * @return int    
     */    
    public int getLineNum() {
        return lineNum;
    }

    /**    
     * 设置行号    
     *    
     * @param lineNum 电话号码    
     */    
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    /**    
     * 得到名字    
     *    
     * @return java.lang.String    
     */    
    public String getName() {
        return name;
    }

    /**    
     * 设置名称    
     *    
     * @param name 名称    
     */    
    public void setName(String name) {
        this.name = name;
    }

    /**    
     * 得到价值    
     *    
     * @return java.lang.String    
     */    
    public String getValue() {
        return value;
    }

    /**    
     * 设定值    
     *    
     * @param value 值    
     */    
    public void setValue(String value) {
        this.value = value;
    }

    /**    
     * 获取参数    
     *    
     * @return java.lang.String    
     */    
    public String[] getParameters() {
        return parameters;
    }

    /**    
     * 设置参数    
     *    
     * @param parameters 参数    
     */    
    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "DocTag{" +
                "lineNum=" + lineNum +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
