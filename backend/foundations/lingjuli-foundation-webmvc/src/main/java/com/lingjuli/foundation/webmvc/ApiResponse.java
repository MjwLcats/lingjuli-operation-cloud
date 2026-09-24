package com.lingjuli.foundation.webmvc;

public record ApiResponse<T>(String code, String message, T data, String requestId) {
    public static <T> ApiResponse<T> success(T data, String requestId) {
        return new ApiResponse<>("0", "success", data, requestId);
    }

    public static <T> ApiResponse<T> failure(String code, String message, String requestId) {
        return new ApiResponse<>(code, message, null, requestId);
    }
}
