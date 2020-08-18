package com.wap.chun.error;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter @Builder
public class ErrorEntity {
    private int code;
    private String message;
    private String method;
    private String requestUrl;
    private Map<String, String[]> parameterMap;
}
