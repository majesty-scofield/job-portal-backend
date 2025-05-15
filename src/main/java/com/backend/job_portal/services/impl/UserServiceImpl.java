package com.backend.job_portal.services.impl;

import com.backend.job_portal.dtos.requests.LoginRequest;
import com.backend.job_portal.dtos.requests.UserRequest;
import com.backend.job_portal.dtos.responses.MessageResponse;
import com.backend.job_portal.dtos.responses.UserResponse;
import com.backend.job_portal.exceptions.AppException;
import com.backend.job_portal.mappers.UserMapper;
import com.backend.job_portal.models.OTP;
import com.backend.job_portal.models.User;
import com.backend.job_portal.repositories.OtpRepository;
import com.backend.job_portal.repositories.UserRepository;
import com.backend.job_portal.services.UserService;
import com.backend.job_portal.utils.Data;
import com.backend.job_portal.utils.Utilities;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final OtpRepository otpRepository;

    @Value("${app.from-address}")
    private String fromEmail;

    @Override
    public UserResponse registerUser(UserRequest request) throws AppException {
        // 1. Check if user with email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new AppException(format("User with email '%s' already exists.", request.getEmail()));
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
    public UserResponse loginUser(LoginRequest request) throws AppException {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new AppException("Invalid email or password");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException("Invalid email or password");
        }
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public void sendOtp(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AppException("User not found!");
        }

        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);

        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setFrom(fromEmail);
        String geneOtp = Utilities.generateOtp();

        OTP otp = new OTP(email, geneOtp, LocalDateTime.now());
        message.setText(Data.getOtpMessageBody(user.getName(), geneOtp), true);

        otpRepository.save(otp);
        mailSender.send(mm);
    }

    @Override
    public void verifyOtp(String email, String otp) {
        OTP newOtp = otpRepository.findById(email).orElseThrow(() -> new AppException("OTP not found"));
        if (!newOtp.getOtpCode().equals(otp)) {
            throw new AppException("Invalid OTP");
        }
    }

    @Override
    public MessageResponse changePassword(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new AppException("User not found!");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return new MessageResponse("Password changed successfully!");
    }

    @Scheduled(fixedRate = 60000)
    public void removeExpiredOtp() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(10);
        List<OTP> expiredOtps = otpRepository.findByCreationTimeBefore(expirationTime);
        if (!expiredOtps.isEmpty()) {
            otpRepository.deleteAll();
            System.out.printf("Expired OTPs %s deleted successfully!%n", expiredOtps.size());
        }
    }
}
