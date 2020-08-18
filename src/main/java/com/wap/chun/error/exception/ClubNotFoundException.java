package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

public class ClubNotFoundException extends ErrorCodeException {
    public ClubNotFoundException() {
        this(ErrorCode.CLUB_CANNOT_FOUND);
    }

    private ClubNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
