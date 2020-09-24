package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;

public class MemberNotFoundException extends ErrorCodeException {
    public MemberNotFoundException() {
        this(ErrorCode.MEMBER_CANNOT_FOUND);
    }

    private MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
