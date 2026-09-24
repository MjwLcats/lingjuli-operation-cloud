package com.lingjuli.foundation.webmvc;

import com.lingjuli.foundation.core.error.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ApiResponse<Void> handleBusiness(BusinessException exception, HttpServletRequest request) {
        return ApiResponse.failure(exception.getErrorCode().code(), exception.getMessage(), requestId(request));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<Void> handleValidation(Exception exception, HttpServletRequest request) {
        return ApiResponse.failure("SYS-VALIDATION-001", "request validation failed", requestId(request));
    }

    private String requestId(HttpServletRequest request) {
        return request.getHeader("X-Request-Id");
    }
}
