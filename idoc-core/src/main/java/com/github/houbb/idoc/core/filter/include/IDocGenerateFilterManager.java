package com.github.houbb.idoc.core.filter.include;

import com.github.houbb.idoc.api.core.filter.IDocGenerateFilter;
import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.exception.IDocRuntimeException;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.core.api.generator.IDocGeneratorManager;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 文档生成包含过滤器管理类
 * @author binbin.hou
 * @since 0.0.1
 */
public class IDocGenerateFilterManager implements IDocGenerateFilter {

    /**
     * 日志
     */
    private final Log log = LogFactory.getLog(IDocGeneratorManager.class);

    /**
     * 包含过滤器配置
     */
    private final String[] generateFilters;

    /**
     * 过滤器列表
     */
    private List<IDocGenerateFilter> docGenerateFilterList = new ArrayList<>();

    public IDocGenerateFilterManager(final String[] generateFilters) {
        this.generateFilters = generateFilters;
        this.initIncludeFilter();
    }

    /**
     * 执行列表的过滤
     * @param docClassList 原始列表
     * @return 过滤后的列表
     */
    public List<DocClass> filter(final List<DocClass> docClassList) {
        if (CollectionUtil.isEmpty(docClassList)) {
            return Collections.emptyList();
        }
        List<DocClass> resultList = new ArrayList<>();
        for(DocClass docClass : docClassList) {
            if(this.include(docClass)) {
                resultList.add(docClass);
            }
        }
        return resultList;
    }

    /**
     * 是否包含
     * 1. 如果没有包含过滤器，则默认都包含
     * 2. 如果有包含过滤器，则必须在指定的过滤器中，才视为包含。
     *
     * @param docClass 类
     * @return 是否包含
     */
    @Override
    public boolean include(final DocClass docClass) {
        if (CollectionUtil.isEmpty(docGenerateFilterList)) {
            return true;
        }
        for (IDocGenerateFilter docIncludeFilter : docGenerateFilterList) {
            if (docIncludeFilter.include(docClass)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 初始化过滤器列表
     */
    private void initIncludeFilter() {
        if(ArrayUtil.isEmpty(this.generateFilters)) {
            return;
        }

        try {
            for(String includeFilter : generateFilters) {
                IDocGenerateFilter iDocGenerateFilter = (IDocGenerateFilter) Class.forName(includeFilter).newInstance();
                docGenerateFilterList.add(iDocGenerateFilter);
            }
        } catch (Exception e) {
            log.error("包含过滤器初始化失败: ", e);
            throw new IDocRuntimeException(e);
        }
    }

}
