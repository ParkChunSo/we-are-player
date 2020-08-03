package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidJwtAuthenticationException extends AuthenticationException{
    public InvalidJwtAuthenticationException() {
        this(ErrorCode.INVALID_TOKEN);
    }

    public InvalidJwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
