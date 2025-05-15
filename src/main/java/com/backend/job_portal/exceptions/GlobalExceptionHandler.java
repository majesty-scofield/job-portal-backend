package com.backend.job_portal.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handle specific Exceptions
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetail> handleAppException(AppException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                request.getDescription(false),
                e.getMessage(),
                HttpStatus.NOT_FOUND.name()
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    // Handle generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                request.getDescription(false),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.name()
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorDetail> handleValidatorException(Exception e, WebRequest request) {
        String message = "Validation failed";
        if (e instanceof MethodArgumentNotValidException methodException) {
            message = methodException.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
        } else {
            ConstraintViolationException cvException = (ConstraintViolationException) e;
            message = cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        }

        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                request.getDescription(false),
                message,
                HttpStatus.BAD_REQUEST.name()
        );

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}