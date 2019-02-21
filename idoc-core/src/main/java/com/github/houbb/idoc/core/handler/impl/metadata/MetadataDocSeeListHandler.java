/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.core.handler.impl.metadata;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.common.handler.AbstractHandler;
import com.github.houbb.idoc.common.util.ArrayUtil;
import com.thoughtworks.qdox.model.DocletTag;

/**
 * <p> 获取 @see 列表信息 </p>
 *
 * <pre> Created: 2019/2/21 10:50 PM  </pre>
 * <pre> Project: idoc  </pre>
 *
 * @author houbinbin
 * @since 0.0.2
 */
public class MetadataDocSeeListHandler extends AbstractHandler<DocletTag, DocClass> {

    @Override
    protected DocClass doHandle(DocletTag docletTag) {
        DocClass docClass = new DocClass();
        String[] strings = docletTag.getParameters();
        // 根据名称的指定信息做匹配
        //1. 第一个参数是 @see 信息全称
        //2. 第二个参数是 备注
        if(ArrayUtil.isNotEmpty(strings)) {
            final String className = strings[0];
            docClass.setFullName(className);
            //TODO: 其他信息的获取
            // 可以在 maven 执行的时候，放置一个大大的 context，初始化所有的 javaclass 信息。
        }
        return docClass;
    }
}
