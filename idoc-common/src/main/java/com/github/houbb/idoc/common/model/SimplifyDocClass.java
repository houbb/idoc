package com.github.houbb.idoc.common.model;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyDocClass extends SimplifyDocBase {

    /**
     * 包名称
     */
    private String packageName;

    /**
     * 方法列表
     */
    private List<SimplifyDocMethod> methods;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<SimplifyDocMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<SimplifyDocMethod> methods) {
        this.methods = methods;
    }
}
