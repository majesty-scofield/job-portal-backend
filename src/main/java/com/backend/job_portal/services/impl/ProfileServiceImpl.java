package com.backend.job_portal.services.impl;

import com.backend.job_portal.dtos.ProfileDto;
import com.backend.job_portal.exceptions.AppException;
import com.backend.job_portal.mappers.ProfileMapper;
import com.backend.job_portal.models.Profile;
import com.backend.job_portal.repositories.ProfileRepository;
import com.backend.job_portal.services.ProfileService;
import com.backend.job_portal.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email) {
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDto getProfile(Long id) throws AppException {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new AppException("Profile not found"));
        return ProfileMapper.mapToProfileDto(profile);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto request) throws AppException {
        profileRepository.findById(request.getId()).orElseThrow(() -> new AppException("Profile not found"));
        Profile savedProfile = profileRepository.save(ProfileMapper.mapToProfile(request));
        return ProfileMapper.mapToProfileDto(savedProfile);
    }

    @Override
    public void deleteProfile(Long id) throws AppException {
        profileRepository.deleteById(id);
    }
}
