package com.wap.chun.security.error;

public class InvalidJwtAuthenticationException extends AuthenticationException{

    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
