package com.backend.job_portal.services;

import com.backend.job_portal.dtos.requests.LoginRequest;
import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.MessageResponse;
import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.exceptions.AppException;
import jakarta.validation.Valid;

public interface UserService {
    UserResponse registerUser(UserRequest request) throws AppException;

    UserResponse loginUser(LoginRequest request) throws AppException;

    void sendOtp(String email) throws Exception;

    void verifyOtp(String email, String otp) throws AppException;

    MessageResponse changePassword(@Valid LoginRequest request) throws AppException;
}
