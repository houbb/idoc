package com.github.houbb.idoc.api.exception;

/**
 * 运行时异常
 * @author binbin.hou
 * @since 0.0.1
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
