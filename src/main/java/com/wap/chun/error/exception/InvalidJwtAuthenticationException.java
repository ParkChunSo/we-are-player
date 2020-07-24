package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

public class InvalidJwtAuthenticationException extends ErrorCodeException{
    public InvalidJwtAuthenticationException() {
        this(ErrorCode.INVALID_TOKEN);
    }

    public InvalidJwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
