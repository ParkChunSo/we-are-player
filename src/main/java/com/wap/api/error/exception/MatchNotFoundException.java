package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class MatchNotFoundException extends ErrorCodeException {
    public MatchNotFoundException(){
        this(ErrorCode.MATCH_NOT_FOUND);
    }
    private MatchNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
