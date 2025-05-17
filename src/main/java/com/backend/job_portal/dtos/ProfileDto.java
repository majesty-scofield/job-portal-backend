package com.backend.job_portal.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private Long id;
    private String name;
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
