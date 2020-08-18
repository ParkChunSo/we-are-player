package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class AuthorizationException extends ErrorCodeException {
    public AuthorizationException(){
        this(ErrorCode.AUTHORIZATION);
    }
    public AuthorizationException(ErrorCode errorCode){
        super(errorCode);
    }
}
