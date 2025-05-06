package com.backend.job_portal.services;

import com.backend.job_portal.dtos.requests.LoginRequest;
import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.exceptions.AppException;

public interface UserService {
    UserResponse registerUser(UserRequest request) throws AppException;

    UserResponse loginUser(LoginRequest request);
}
