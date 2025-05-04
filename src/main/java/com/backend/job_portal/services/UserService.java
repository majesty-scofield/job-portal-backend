package com.backend.job_portal.services;

import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest request);
}
