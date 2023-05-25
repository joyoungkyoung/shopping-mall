package com.nicky.shoppingmall.config.business;

import com.nicky.shoppingmall.config.error.ErrorInfo;

public class BusinessException extends RuntimeException {
    protected int code;
    protected String message;

    public BusinessException(ErrorInfo error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
