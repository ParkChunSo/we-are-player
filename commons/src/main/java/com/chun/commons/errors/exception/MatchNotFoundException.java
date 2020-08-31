package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;

public class MatchNotFoundException extends ErrorCodeException {
    public MatchNotFoundException(){
        this(ErrorCode.MATCH_NOT_FOUND);
    }
    private MatchNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
