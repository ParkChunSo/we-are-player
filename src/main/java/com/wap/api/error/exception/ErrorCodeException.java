package com.wap.api.error.exception;

import com.wap.api.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorCodeException extends RuntimeException{
    private final ErrorCode errorCode;
}
