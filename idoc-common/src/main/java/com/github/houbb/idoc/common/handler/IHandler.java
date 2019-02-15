/*
 * Copyright (c)  2019. houbinbin Inc.
 * idoc All rights reserved.
 */

package com.github.houbb.idoc.common.handler;

/**
 * 处理
 * @param <T> 泛型入参
 * @param <R> 泛型结果
 * @author binbin.hou
 */
public interface IHandler<T, R> {

    /**
     * 处理
     * @param t 模板
     * @return 结果
     */
    R handle(T t);

}
