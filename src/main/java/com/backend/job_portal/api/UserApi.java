package com.backend.job_portal.api;

import com.backend.job_portal.dtos.requests.LoginRequest;
import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.MessageResponse;
import com.backend.job_portal.dtos.responses.OtpResponse;
import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.exceptions.AppException;
import com.backend.job_portal.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }

    @RequestMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody @Valid LoginRequest request) throws AppException {
        return new ResponseEntity<>(userService.changePassword(request), HttpStatus.OK);
    }

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<OtpResponse> sendOpt(@PathVariable @Email(message = "Invalid email address!") String email) throws Exception {
        userService.sendOtp(email);
        return new ResponseEntity<>(new OtpResponse("OTP sent successfully!"), HttpStatus.OK);
    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<OtpResponse> verifyOtp(
            @PathVariable @Email(message = "Invalid email address!") String email,
            @PathVariable @Pattern(regexp = "^[0-9]{6}$", message = "OTP invalid") String otp) throws AppException {
        userService.verifyOtp(email, otp);
        return new ResponseEntity<>(new OtpResponse("OTP has been verified!"), HttpStatus.OK);
    }
}
