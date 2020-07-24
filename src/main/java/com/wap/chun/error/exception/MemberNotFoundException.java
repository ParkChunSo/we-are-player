package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

public class MemberNotFoundException extends ErrorCodeException {
    public MemberNotFoundException() {
        this(ErrorCode.MEMBER_CANNOT_FOUND);
    }

    private MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
