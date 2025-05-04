package com.backend.job_portal.dtos.requests;

import com.backend.job_portal.enums.AccountType;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private AccountType accountType;
}
