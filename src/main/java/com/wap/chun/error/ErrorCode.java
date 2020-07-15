package com.wap.chun.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    ERROR_CODE(0, "example", "this is example error code");
    private final int status;
    private final String code;
    private final String message;

}
