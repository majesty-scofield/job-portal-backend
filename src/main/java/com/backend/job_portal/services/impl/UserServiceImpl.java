package com.backend.job_portal.services.impl;

import com.backend.job_portal.dtos.requests.LoginRequest;
import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.exceptions.AppException;
import com.backend.job_portal.mappers.UserMapper;
import com.backend.job_portal.models.User;
import com.backend.job_portal.repositories.UserRepository;
import com.backend.job_portal.services.UserService;
import com.backend.job_portal.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse registerUser(UserRequest request) throws AppException {
        // 1. Check if user with email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new AppException("User with email already exists.");
        }

        // 2. Build the user object
        User user = User.builder()
                .id(Utilities.getNextSequence("users"))
                .name(request.getName())
                .email(request.getEmail())
                .accountType(request.getAccountType())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        // 3. Save and return
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserResponse loginUser(LoginRequest request) throws AppException{
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null && !passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new AppException("Invalid email or password");
        return UserMapper.mapToUserDto(user);
    }

}
