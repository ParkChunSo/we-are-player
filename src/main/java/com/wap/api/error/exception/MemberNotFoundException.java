package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class MemberNotFoundException extends ErrorCodeException {
    public MemberNotFoundException() {
        this(ErrorCode.MEMBER_CANNOT_FOUND);
    }

    private MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
