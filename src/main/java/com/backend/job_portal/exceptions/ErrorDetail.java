package com.backend.job_portal.exceptions;

import java.time.LocalDateTime;

public record ErrorDetail(
        LocalDateTime timestamp,
        String details,
        String message,
        String errorCode
) {
}