package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;

public class SubmitNotFoundException extends ErrorCodeException {

    public SubmitNotFoundException(){
        this(ErrorCode.SUBMIT_NOT_FOUND);
    }
    public SubmitNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
