package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error",  HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error",   HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed" , HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters" , HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters" , HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed" , HttpStatus.NOT_ACCEPTABLE),
    UNAUTHENTICATED(1006 , "Unautheticated" , HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007 , "You do not have permission" , HttpStatus.FORBIDDEN),
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
