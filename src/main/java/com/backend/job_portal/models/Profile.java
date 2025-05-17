package com.backend.job_portal.models;

import com.backend.job_portal.dtos.Certification;
import com.backend.job_portal.dtos.Experience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {
    @Id
    private Long id;
    private String name;
    private String role;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    private String image;
    private String coverPicture;
    private List<String> skills;
    private List<Experience> experiences;
    private List<Certification> certifications;
}
