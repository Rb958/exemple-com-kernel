package com.afrikpay.security.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormatedResponse<T> {
    private int code;
    private String message;
    private T content;

    public FormatedResponse() {
        code = 200;
        message = "success";
        content = null;
    }

    public FormatedResponse(T content) {
        code = 200;
        message = "success";
        this.content = content;
    }

    public FormatedResponse(int code, String message) {
        content = null;
        this.code = code;
        this.message = message;
    }

    public FormatedResponse(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static FormatedResponse<Exception> hydrateWithException(Exception e){
        // TODO Personalize error when it's a known Exception or else return "Internal server error"
        return new FormatedResponse<>(500, e.getMessage());
    }
}
