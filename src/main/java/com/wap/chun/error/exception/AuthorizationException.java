package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;

public class AuthorizationException extends ErrorCodeException {
    public AuthorizationException(){
        this(ErrorCode.AUTHORIZATION);
    }
    public AuthorizationException(ErrorCode errorCode){
        super(errorCode);
    }
}
