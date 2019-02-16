package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocField;
import com.github.houbb.idoc.api.model.metadata.DocMethod;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.util.ObjectUtil;
import com.github.houbb.idoc.core.constant.JavaTagConstant;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;

/**
 * 处理字段
 * @author binbin.hou
 * @since 0.0.1
 */
public class MetadataDocFieldHandler extends AbstractHandler<JavaField, DocField> {
    @Override
    protected DocField doHandle(JavaField javaField) {
        DocField docField = new DocField();
        docField.setName(javaField.getName());
        docField.setType(javaField.getType().getFullyQualifiedName());
        docField.setComment(javaField.getComment());
        // 使用 doclet，缺点：严格的 java-doc 会报错
        // 使用判断的方式，会导致处理其他特别麻烦。
        DocletTag requireTag = javaField.getTagByName(JavaTagConstant.IDOC_REQUIRE);
        DocletTag remarkTag = javaField.getTagByName(JavaTagConstant.IDOC_REMARK);

        if (ObjectUtil.isNotNull(requireTag)) {
            docField.setRequired(requireTag.getValue());
        }
        if (ObjectUtil.isNotNull(remarkTag)) {
            docField.setRemark(remarkTag.getValue());
        }
        return docField;
    }
}
