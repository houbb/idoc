package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocPackage;
import com.github.houbb.idoc.common.handler.IHandler;
import com.thoughtworks.qdox.model.JavaPackage;

/**
 *
 * @author binbin.hou
 * date 2019/2/21
 * @since
 */
public class MetadataDocPackageHandler implements IHandler<JavaPackage, DocPackage> {
    @Override
    public DocPackage handle(JavaPackage javaPackage) {
        return null;
    }
}
