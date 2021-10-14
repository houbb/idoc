package com.github.houbb.idoc.common.handler.impl.simplify;

import com.github.houbb.idoc.api.model.metadata.DocClass;
import com.github.houbb.idoc.api.model.metadata.DocMethod;
import com.github.houbb.idoc.common.handler.IHandler;
import com.github.houbb.idoc.common.model.SimplifyDocClass;
import com.github.houbb.idoc.common.model.SimplifyDocMethod;
import com.github.houbb.idoc.common.util.CollectionUtil;
import com.github.houbb.idoc.common.util.CommentUtil;

import java.util.List;

/**
 * 通用类转换工具
 * @author binbin.hou
 * @since 0.0.1
 */
public class SimplifyClassHandler implements IHandler<DocClass, SimplifyDocClass> {
    @Override
    public SimplifyDocClass handle(DocClass docClass) {
        SimplifyDocClass simplifyDocClass = new SimplifyDocClass();
        simplifyDocClass.setName(docClass.getName());
        simplifyDocClass.setPackageName(docClass.getPackageName());
        simplifyDocClass.setComment(docClass.getComment());
        simplifyDocClass.setRemark(docClass.getRemark());

        //v0.2.0 添加第一行备注，避免过多，导致格式错乱
        String commentFirstLine = CommentUtil.getFirstLine(docClass.getComment());
        simplifyDocClass.setCommentFirstLine(commentFirstLine);

        // 方法信息
        List<DocMethod> docMethodList = docClass.getDocMethodList();
        List<SimplifyDocMethod> commonDocMethodList = CollectionUtil.buildList(docMethodList, new SimplifyMethodHandler());
        simplifyDocClass.setMethods(commonDocMethodList);

        return simplifyDocClass;
    }

}
