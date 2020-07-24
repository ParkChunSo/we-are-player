package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

@Getter
public class AccessDeniedAuthenticationException extends ErrorCodeException{
    public AccessDeniedAuthenticationException() {
        this(ErrorCode.HANDLE_ACCESS_DENIED);
    }

    private AccessDeniedAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
