package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class ClubAlreadyExistException extends ErrorCodeException {
    public ClubAlreadyExistException(){
        this(ErrorCode.CLUB_DUPLICATION);
    }
    private ClubAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
