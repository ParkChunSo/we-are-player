package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class ClubNotFoundException extends ErrorCodeException {
    public ClubNotFoundException() {
        this(ErrorCode.CLUB_CANNOT_FOUND);
    }

    private ClubNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
