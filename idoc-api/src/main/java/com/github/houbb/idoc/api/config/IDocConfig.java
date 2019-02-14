package com.github.houbb.idoc.api.config;

/**
 * 文档生成配置信息
 * @author binbin.hou
 * @since 0.0.1
 */
public class IDocConfig {

    /**
     * 是否覆盖编写
     */
    private boolean isOverwriteWhenExists;

    /**
     * 所有的内容生成一个文件
     */
    private boolean isAllInOne;

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
}
