package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class ClubMemberNotFoundException extends ErrorCodeException {
    public ClubMemberNotFoundException(){
        this(ErrorCode.MEMBER_CANNOT_FOUND_IN_CLUB);
    }

    private ClubMemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
