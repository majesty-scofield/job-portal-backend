package com.backend.job_portal.repositories;

import com.backend.job_portal.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, Long> {
}
