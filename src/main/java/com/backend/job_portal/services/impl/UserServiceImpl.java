package com.backend.job_portal.services.impl;

import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.mappers.UserMapper;
import com.backend.job_portal.models.User;
import com.backend.job_portal.repositories.UserRepository;
import com.backend.job_portal.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponse registerUser(UserRequest request) {
        User user = User
                .builder()
                .name(request.getName())
                .email(request.getEmail())
                .accountType(request.getAccountType())
                .password(request.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }
}
