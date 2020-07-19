package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

public class ClubMemberNotFoundException extends ErrorCodeException {
    public ClubMemberNotFoundException(){
        this(ErrorCode.MEMBER_CANNOT_FOUND_IN_CLUB);
    }

    private ClubMemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
