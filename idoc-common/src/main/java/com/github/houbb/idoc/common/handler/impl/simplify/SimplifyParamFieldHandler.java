package com.github.houbb.idoc.common.handler.impl.simplify;

import com.github.houbb.idoc.api.model.metadata.DocField;
import com.github.houbb.idoc.api.model.metadata.DocParameter;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.model.SimplifyDocField;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.common.util.ObjectUtil;

import java.util.List;

/**
 * 通用入参处理
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyParamFieldHandler implements IHandler<DocParameter, SimplifyDocField> {
    @Override
    public SimplifyDocField handle(DocParameter docParameter) {
        if(ObjectUtil.isNull(docParameter)) {
            return null;
        }

        SimplifyDocField simplifyDocField = new SimplifyDocField();
        simplifyDocField.setName(docParameter.getName());
        simplifyDocField.setComment(docParameter.getComment());
        simplifyDocField.setRemark(docParameter.getRemark());
        simplifyDocField.setType(docParameter.getType());

        // 入参为特殊参数列表
        List<DocField> docFieldList =  docParameter.getDocFieldList();
        if(CollectionUtil.isNotEmpty(docFieldList)) {
            List<SimplifyDocField> simplifyDocFieldList = CollectionUtil.buildList(docFieldList,
                    new SimplifyDocFieldHandler());
            simplifyDocField.setEntries(simplifyDocFieldList);
        }
        return simplifyDocField;
    }
}
