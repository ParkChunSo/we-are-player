package com.chun.commons.errors.exception;

import com.chun.commons.errors.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorCodeException extends RuntimeException{
    private final ErrorCode errorCode;
}
