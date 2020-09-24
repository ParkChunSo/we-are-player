package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;

public class ClubAlreadyExistException extends ErrorCodeException {
    public ClubAlreadyExistException(){
        this(ErrorCode.CLUB_DUPLICATION);
    }
    private ClubAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
