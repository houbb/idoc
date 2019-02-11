package com.github.houbb.idoc.core.exception;

/**
 * @author binbin.hou
 * date 2019/2/11
 */
public class IDocRuntimeException extends RuntimeException {

    public IDocRuntimeException() {
    }

    public IDocRuntimeException(String message) {
        super(message);
    }

    public IDocRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IDocRuntimeException(Throwable cause) {
        super(cause);
    }

    public IDocRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
