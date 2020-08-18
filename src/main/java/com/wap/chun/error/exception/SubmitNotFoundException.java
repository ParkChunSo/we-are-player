package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;

public class SubmitNotFoundException extends ErrorCodeException {

    public SubmitNotFoundException(){
        this(ErrorCode.SUBMIT_NOT_FOUND);
    }
    public SubmitNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
