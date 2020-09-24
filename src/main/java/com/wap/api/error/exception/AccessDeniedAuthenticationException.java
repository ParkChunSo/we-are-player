package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;
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
