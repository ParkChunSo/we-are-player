package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;

public class MemberAlreadyExistException extends ErrorCodeException {
    public MemberAlreadyExistException() {
        this(ErrorCode.EMAIL_DUPLICATION);
    }

    private MemberAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
