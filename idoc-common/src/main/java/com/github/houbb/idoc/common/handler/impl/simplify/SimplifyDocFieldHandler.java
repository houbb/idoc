package com.github.houbb.idoc.common.handler.impl.simplify;

import com.github.houbb.idoc.api.model.metadata.DocField;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.model.SimplifyDocField;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.common.util.ObjectUtil;

import java.util.List;

/**
 * 通用字段处理
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyDocFieldHandler implements IHandler<DocField, SimplifyDocField> {
    @Override
    public SimplifyDocField handle(DocField docField) {
        if(ObjectUtil.isNull(docField)) {
            return null;
        }

        SimplifyDocField commonDocField = new SimplifyDocField();
        commonDocField.setName(docField.getName());
        commonDocField.setComment(docField.getComment());
        commonDocField.setType(docField.getType());
        commonDocField.setRequired(docField.getRequired());
        commonDocField.setRemark(docField.getRemark());

        // 处理特殊字段信息 比如集合字段
        // TODO: 需要在读取的时候进行特殊处理。
        List<DocField> docFieldList = docField.getDocFieldList();
        if(CollectionUtil.isNotEmpty(docFieldList)) {
            List<SimplifyDocField> commonDocFieldList = CollectionUtil.buildList(docFieldList,
                    new SimplifyDocFieldHandler());
            commonDocField.setEntries(commonDocFieldList);
        }

        return commonDocField;
    }
}
