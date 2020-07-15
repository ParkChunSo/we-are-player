package com.wap.chun.error.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationException extends RuntimeException{
    private final String message;
}
