package com.backend.job_portal.services;

import com.backend.job_portal.dtos.ProfileDto;
import com.backend.job_portal.exceptions.AppException;

public interface ProfileService {
    Long createProfile(String email) throws AppException;
    ProfileDto getProfile(Long id) throws AppException;
    ProfileDto updateProfile(ProfileDto request) throws AppException;
    void deleteProfile(Long id) throws AppException;
}
