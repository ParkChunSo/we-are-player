package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;

public class MatChNotFoundException extends ErrorCodeException {
    public MatChNotFoundException(){
        this(ErrorCode.MATCH_NOT_FOUND);
    }
    private MatChNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}