package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;

public class InvitationNotFoundException extends ErrorCodeException{

    public InvitationNotFoundException(){
        this(ErrorCode.INVITATION_NOT_FOUND);
    }
    public InvitationNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
