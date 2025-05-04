package com.backend.job_portal.exceptions;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}