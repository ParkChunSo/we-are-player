package com.wap.chun.error.handler;

import com.wap.chun.error.ErrorEntity;
import com.wap.chun.error.exception.AlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//TODO("AOP를 사용해서 로깅")
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorEntity> handleException(HttpServletRequest request, AlreadyExistException exception){
        ErrorEntity entity = ErrorEntity.builder()
                    .status(exception.getErrorCode().getStatus())
                    .message(exception.getErrorCode().getMessage())
                    .method(request.getMethod())
                    .requestUrl(request.getRequestURL().toString())
                    .parameterMap(request.getParameterMap())
                    .build();

        return new ResponseEntity<>(entity, HttpStatus.resolve(exception.getErrorCode().getStatus()));
    }
}
