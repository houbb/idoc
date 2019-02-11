package com.github.houbb.idoc.core.handler;

import com.github.houbb.idoc.core.exception.IDocRuntimeException;

/**
 * 处理
 * @param <T> 泛型入参
 * @param <R> 泛型结果
 * @author binbin.hou
 */
public interface Handler<T, R> {

    /**
     * 处理
     * @param t 模板
     * @throws IDocRuntimeException if any
     * @return 结果
     */
    R handle(T t) throws IDocRuntimeException;

}
