package com.onlineshop.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_EXISTED(1001, "Username already exists"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
