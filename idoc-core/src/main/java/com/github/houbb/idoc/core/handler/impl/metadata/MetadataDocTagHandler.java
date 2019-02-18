package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocTag;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.handler.IHandler;
import com.thoughtworks.qdox.model.DocletTag;

/**
 * doclet 标签处理
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocTagHandler extends AbstractHandler<DocletTag, DocTag> {
    @Override
    protected DocTag doHandle(DocletTag docletTag) {
        DocTag docTag = new DocTag();
        docTag.setName(docletTag.getName());
        docTag.setLineNum(docletTag.getLineNumber());
        docTag.setParameters(docletTag.getParameters());
        docTag.setValue(docletTag.getValue());
        return docTag;
    }
}
