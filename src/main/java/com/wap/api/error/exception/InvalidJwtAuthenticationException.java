package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class InvalidJwtAuthenticationException extends ErrorCodeException{
    public InvalidJwtAuthenticationException() {
        this(ErrorCode.INVALID_TOKEN);
    }

    public InvalidJwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
