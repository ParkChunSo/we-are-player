package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

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
