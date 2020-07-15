package com.wap.chun.error.exception;

public class InvalidJwtAuthenticationException extends AuthenticationException{

    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
