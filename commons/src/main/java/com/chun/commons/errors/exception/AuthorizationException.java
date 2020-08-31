package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;

public class AuthorizationException extends ErrorCodeException {
    public AuthorizationException(){
        this(ErrorCode.AUTHORIZATION);
    }
    public AuthorizationException(ErrorCode errorCode){
        super(errorCode);
    }
}
