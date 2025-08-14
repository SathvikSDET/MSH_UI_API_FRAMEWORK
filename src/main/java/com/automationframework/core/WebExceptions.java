package com.automationframework.core;

/**
 * Custom exception class for handling web automation exceptions
 */
public class WebExceptions extends Exception {
    
    public enum ExceptionType {
        ELEMENT_NOT_FOUND,
        ELEMENT_NOT_CLICKABLE,
        ELEMENT_NOT_VISIBLE,
        TIMEOUT_EXCEPTION,
        STALE_ELEMENT_EXCEPTION,
        WEB_DRIVER_EXCEPTION,
        GENERAL_EXCEPTION
    }
    
    private final ExceptionType exceptionType;
    
    public WebExceptions(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
    
    public WebExceptions(ExceptionType exceptionType, String message, Throwable cause) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
    
    public ExceptionType getExceptionType() {
        return exceptionType;
    }
    
    @Override
    public String toString() {
        return "WebExceptions{" +
                "exceptionType=" + exceptionType +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
