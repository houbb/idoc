package com.github.houbb.idoc.common.handler.impl.simplify;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.metadata.DocField;
import com.github.houbb.idoc.api.model.metadata.DocMethod;
import com.github.houbb.idoc.api.model.metadata.DocParameter;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.model.SimplifyDocField;
import com.github.houbb.idoc.common.model.SimplifyDocMethod;
import com.github.houbb.idoc.common.util.CollectionUtil;

import java.util.*;

/**
 * @author binbin.hou
 * date 2019/2/15
 */
public class SimplifyMethodHandler implements IHandler<DocMethod, SimplifyDocMethod> {
    @Override
    public SimplifyDocMethod handle(DocMethod docMethod) {
        if(null == docMethod) {
            return null;
        }
        SimplifyDocMethod commonDocMethod = new SimplifyDocMethod();
        commonDocMethod.setComment(docMethod.getComment());
        commonDocMethod.setRemark(docMethod.getRemark());
        commonDocMethod.setName(docMethod.getName());

        // 处理入参
        final List<SimplifyDocField> params = buildParams(docMethod.getDocParameterList());

        // 处理出参
        final List<SimplifyDocField> returns = buildRuturns(docMethod.getDocReturnClass());

        commonDocMethod.setParams(params);
        commonDocMethod.setReturns(returns);
        commonDocMethod.setParamDetails(buildFieldDetails(params));
        commonDocMethod.setReturnDetails(buildFieldDetails(returns));
        return commonDocMethod;
    }

    /**
     * 构建入参信息
     * @param docParameterList 原始参数列表
     * @return 构建后的参数结果
     */
    private List<SimplifyDocField> buildParams(final List<DocParameter> docParameterList) {
        return CollectionUtil.buildList(docParameterList, new SimplifyParamFieldHandler());
    }

    /**
     * 构建返回值结果
     * @param returnDocClass 返回结果
     * @return 构建后的参数列表
     */
    private List<SimplifyDocField> buildRuturns(final DocClass returnDocClass) {
        if(null == returnDocClass) {
            return Collections.emptyList();
        }

        // 当前返回类的所有字段信息
        final List<DocField> docFieldList = returnDocClass.getDocFieldList();
        return CollectionUtil.buildList(docFieldList, new SimplifyDocFieldHandler());
    }

    /**
     * 构建出参/入参字段明细
     * @param fields 参数列表
     * @return 结果集合
     */
    private Map<String, List<SimplifyDocField>> buildFieldDetails(final List<SimplifyDocField> fields) {
        if(CollectionUtil.isEmpty(fields)) {
            return null;
        }

        final Map<String, List<SimplifyDocField>> map = new LinkedHashMap<>();

        for(SimplifyDocField docField : fields) {
            final String name = docField.getName();
            List<SimplifyDocField> entries = docField.getEntries();
            if(CollectionUtil.isEmpty(entries)) {
                continue;
            }
            map.put(name, entries);

            // 循环处理2层-可以优化
            for(SimplifyDocField entryDocField : entries) {
                final List<SimplifyDocField> entryFields = entryDocField.getEntries();
                if(CollectionUtil.isNotEmpty(entryFields)) {
                    final String entryName = entryDocField.getName();
                    map.put(entryName, entryFields);
                }
            }
        }
        return map;
    }

}
