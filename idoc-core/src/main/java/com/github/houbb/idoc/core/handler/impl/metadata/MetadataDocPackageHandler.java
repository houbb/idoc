package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocPackage;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.handler.IHandler;
import com.thoughtworks.qdox.model.JavaPackage;

/**
 * 包信息处理类
 * @author binbin.hou
 * date 2019/2/21
 * @since 0.0.2
 */
public class MetadataDocPackageHandler extends AbstractHandler<JavaPackage, DocPackage> {
    @Override
    protected DocPackage doHandle(JavaPackage target) {
        DocPackage docPackage = new DocPackage();
        docPackage.setName(target.getName());
        return docPackage;
    }
}
