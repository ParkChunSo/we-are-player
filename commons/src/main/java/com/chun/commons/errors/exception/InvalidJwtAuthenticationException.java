package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;

public class InvalidJwtAuthenticationException extends ErrorCodeException{
    public InvalidJwtAuthenticationException() {
        this(ErrorCode.INVALID_TOKEN);
    }

    public InvalidJwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
