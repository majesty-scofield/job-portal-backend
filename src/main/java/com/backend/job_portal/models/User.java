package com.backend.job_portal.models;

import com.backend.job_portal.enums.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    private String name;
    @Indexed(unique = true)
    @Email(message = "The email is not valid")
    private String email;
    @NotBlank(message = "The password is required")
    private String password;
    private AccountType accountType = AccountType.APPLICANT;
    private Long profileId;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
