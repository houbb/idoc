package com.github.houbb.idoc.core.core;


import com.github.houbb.idoc.common.exception.IDocRuntimeException;

/**
 * 所有方法的统一执行入口。
 * @author houbinbin
 * @version 0.0.1
 */
public interface ExecuteService {

    /**
     * 执行
     * @throws IDocRuntimeException if any
     */
    void execute() throws IDocRuntimeException;

}
