package com.github.houbb.idoc.common.handler.impl.simplify;

import com.github.houbb.idoc.api.model.metadata.DocField;
import com.github.houbb.idoc.api.model.metadata.DocMethodParameter;
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
public class SimplifyParamFieldHandler implements IHandler<DocMethodParameter, SimplifyDocField> {
    @Override
    public SimplifyDocField handle(DocMethodParameter docMethodParameter) {
        if(ObjectUtil.isNull(docMethodParameter)) {
            return null;
        }

        SimplifyDocField simplifyDocField = new SimplifyDocField();
        simplifyDocField.setName(docMethodParameter.getName());
        simplifyDocField.setComment(docMethodParameter.getComment());
        simplifyDocField.setRemark(docMethodParameter.getRemark());
        simplifyDocField.setType(docMethodParameter.getType());
        simplifyDocField.setTypeAlias(docMethodParameter.getTypeAlias());

        // 入参为特殊参数列表
        List<DocField> docFieldList =  docMethodParameter.getDocFieldList();
        if(CollectionUtil.isNotEmpty(docFieldList)) {
            List<SimplifyDocField> simplifyDocFieldList = CollectionUtil.buildList(docFieldList,
                    new SimplifyDocFieldHandler());
            simplifyDocField.setEntries(simplifyDocFieldList);
        }
        return simplifyDocField;
    }
}
