package com.backend.job_portal.repositories;

import com.backend.job_portal.models.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OtpRepository extends MongoRepository<OTP, String> {
    List<OTP> findByCreationTimeBefore(LocalDateTime expirationTime);
}
