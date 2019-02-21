/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.common.util;

import com.github.houbb.idoc.common.handler.IHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数组工具类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class ArrayUtil {

    /**
     * 是否为空
     * @param objects 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final Object[] objects) {
        return null == objects
                || objects.length == 0;
    }

    public static boolean isNotEmpty(final Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 构建结果列表
     * @param targets 原始信息
     * @param handler 处理接口
     * @param <T> 入参
     * @param <R> 出参
     * @return 结果
     */
    public static <T, R> List<R> buildList(final T[] targets, final IHandler<T, R> handler) {
        if(isEmpty(targets)) {
            return Collections.emptyList();
        }
        List<R> rList = new ArrayList<>(targets.length);
        for(T t : targets) {
            R r = handler.handle(t);
            if(ObjectUtil.isNull(t)) {
                continue;
            }
            rList.add(r);
        }
        return rList;
    }
}
