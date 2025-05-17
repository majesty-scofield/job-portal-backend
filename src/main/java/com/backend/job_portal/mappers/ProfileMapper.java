package com.backend.job_portal.mappers;

import com.backend.job_portal.dtos.ProfileDto;
import com.backend.job_portal.models.Profile;

public class ProfileMapper {
    public static ProfileDto mapToProfileDto(Profile profile) {
        if (profile == null) return null;

        ProfileDto dto = new ProfileDto();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        dto.setJobTitle(profile.getJobTitle());
        dto.setCompany(profile.getCompany());
        dto.setLocation(profile.getLocation());
        dto.setAbout(profile.getAbout());
        dto.setImage(profile.getImage());
        dto.setCoverPicture(profile.getCoverPicture());
        dto.setSkills(profile.getSkills());
        dto.setExperiences(profile.getExperiences());
        dto.setCertifications(profile.getCertifications());

        return dto;
    }

    public static Profile mapToProfile(ProfileDto dto) {
        if (dto == null) return null;

        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setJobTitle(dto.getJobTitle());
        profile.setCompany(dto.getCompany());
        profile.setLocation(dto.getLocation());
        profile.setAbout(dto.getAbout());
        profile.setImage(dto.getImage());
        profile.setCoverPicture(dto.getCoverPicture());
        profile.setSkills(dto.getSkills());
        profile.setExperiences(dto.getExperiences());
        profile.setCertifications(dto.getCertifications());

        return profile;
    }
}

