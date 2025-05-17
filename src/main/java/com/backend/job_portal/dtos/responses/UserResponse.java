package com.backend.job_portal.dtos.responses;

import com.backend.job_portal.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
