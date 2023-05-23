package com.nicky.shoppingmall.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nicky.shoppingmall.config.error.ErrorInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Response {
    private int code = 200;
    protected String message = "Ok";
    protected Object data;

    public Response(Object data) {
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(ErrorInfo errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }
}
