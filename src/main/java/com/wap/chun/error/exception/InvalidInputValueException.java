package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;

public class InvalidInputValueException extends ErrorCodeException{
    public InvalidInputValueException(){
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
    public InvalidInputValueException(ErrorCode errorCode) {
        super(errorCode);
    }
    public InvalidInputValueException(ErrorCode errorCode, String massage){
        this(errorCode);
    }

}
