package com.wap.chun.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter @Builder
@AllArgsConstructor
public class ErrorEntity {
    private int status;
    private String message;
    private String method;
    private String requestUrl;
    private Map<String, String[]> parameterMap;
}
