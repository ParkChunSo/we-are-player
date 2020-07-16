package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.Getter;

@Getter
public class ClubNotFoundException extends NotFoundException {
    public ClubNotFoundException() {
        this(ErrorCode.CLUB_CANNOT_FOUND);
    }

    private ClubNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
