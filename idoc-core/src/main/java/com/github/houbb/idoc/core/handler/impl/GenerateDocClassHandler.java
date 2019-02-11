package com.github.houbb.idoc.core.handler.impl;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.core.exception.IDocRuntimeException;
import com.github.houbb.idoc.core.handler.JavaClassHandler;
import com.thoughtworks.qdox.model.JavaClass;

/**
 * 处理单个的类文件
 * @author binbin.hou
 * date 2019/2/11
 */
public class GenerateDocClassHandler implements JavaClassHandler {
    @Override
    public DocClass handle(JavaClass javaClass) throws IDocRuntimeException {
        return null;
    }
}
