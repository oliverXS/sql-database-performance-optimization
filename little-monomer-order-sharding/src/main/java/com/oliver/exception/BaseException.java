package com.oliver.exception;


/**
 * @author xiaorui
 */
public class BaseException extends RuntimeException {
    /**
     * Exception code
     */
    private String code;
    public BaseException() { super(); }

    public BaseException(String message) { super(message);}

    public BaseException(String message, Throwable e) {
        super(message,e);
    }

    public BaseException(String methodName, String message){
        super(methodName+"|"+ message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
