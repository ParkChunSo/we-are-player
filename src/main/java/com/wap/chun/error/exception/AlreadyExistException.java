package com.wap.chun.error.exception;

import com.wap.chun.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlreadyExistException extends RuntimeException{
    private final ErrorCode errorCode;
}
