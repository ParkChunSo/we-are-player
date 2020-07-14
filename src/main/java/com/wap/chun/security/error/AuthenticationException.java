package com.wap.chun.security.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationException extends RuntimeException{
    private final String message;
}
