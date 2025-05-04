package com.backend.job_portal.mappers;

import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.models.User;

public class UserMapper {
    public static UserResponse mapToUserDto(User user) {
        if (user == null) return null;

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAccountType(user.getAccountType());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
