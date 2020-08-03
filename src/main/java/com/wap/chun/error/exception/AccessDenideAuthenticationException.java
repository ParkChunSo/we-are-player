package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

@Getter
public class AccessDenideAuthenticationException extends AuthenticationException{
    public AccessDenideAuthenticationException() {
        this(ErrorCode.HANDLE_ACCESS_DENIED);
    }

    private AccessDenideAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
