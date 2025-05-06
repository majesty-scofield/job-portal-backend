package com.backend.job_portal.api;

import com.backend.job_portal.dtos.requests.LoginRequest;
import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.exceptions.AppException;
import com.backend.job_portal.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @RequestMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) throws AppException {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @RequestMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest request) throws AppException {
        return new  ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }
}
