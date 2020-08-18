package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class MemberAlreadyExistException extends ErrorCodeException {
    public MemberAlreadyExistException() {
        this(ErrorCode.EMAIL_DUPLICATION);
    }

    private MemberAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
