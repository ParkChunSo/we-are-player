package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

public class MemberAlreadyExistException extends ErrorCodeException {
    public MemberAlreadyExistException() {
        this(ErrorCode.EMAIL_DUPLICATION);
    }

    private MemberAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
