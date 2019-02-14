package com.github.houbb.idoc.api.core.genenrator;

import com.github.houbb.idoc.api.config.IDocConfig;

/**
 * doc 生成抽象类
 * @author binbin.hou
 * @since 0.0.1
 */
public abstract class AbstractDocGenerator implements IDocGenerator {

    @Override
    public IDocConfig getDocConfig() {
        IDocConfig docConfig = new IDocConfig();
        docConfig.setAllInOne(true);
        docConfig.setOverwriteWhenExists(true);
        return docConfig;
    }

}
