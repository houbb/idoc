package com.github.houbb.idoc.core.handler;

import com.github.houbb.idoc.core.exception.IDocRuntimeException;

/**
 * 处理
 * @param <T> 泛型
 */
public interface Handler<T> {

    /**
     * 处理
     * @param t 模板
     * @throws IDocRuntimeException if any
     */
    void handle(T t) throws IDocRuntimeException;

}
