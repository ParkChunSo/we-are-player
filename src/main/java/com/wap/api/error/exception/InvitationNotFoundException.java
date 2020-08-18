package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;

public class InvitationNotFoundException extends ErrorCodeException{

    public InvitationNotFoundException(){
        this(ErrorCode.INVITATION_NOT_FOUND);
    }
    public InvitationNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
