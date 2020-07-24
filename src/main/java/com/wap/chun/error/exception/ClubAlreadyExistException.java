package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

public class ClubAlreadyExistException extends ErrorCodeException {
    public ClubAlreadyExistException(){
        this(ErrorCode.CLUB_DUPLICATION);
    }
    private ClubAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
