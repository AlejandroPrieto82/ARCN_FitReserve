package com.fitreserve.shared.util;

public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final String message;

    public ApiResponse(T data) {
        this.success = true;
        this.data = data;
        this.message = "OK";
    }

    public ApiResponse(T data, String message) {
        this.success = true;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}