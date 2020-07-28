package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;

public class MatchNotFoundException extends ErrorCodeException {
    public MatchNotFoundException(){
        this(ErrorCode.MATCH_NOT_FOUND);
    }
    private MatchNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
