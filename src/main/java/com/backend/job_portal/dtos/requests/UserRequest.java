package com.backend.job_portal.dtos.requests;

import com.backend.job_portal.enums.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "The name is required")
    private String name;
    @NotBlank(message = "The email is required")
    @Email(message = "The email is not valid")
    private String email;
    @NotBlank(message = "The password is required")
    @Size(min = 8, message = "The password must be at least 8 characters long")
    private String password;
    private AccountType accountType;
}
