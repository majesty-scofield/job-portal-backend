package com.backend.job_portal.repositories;

import com.backend.job_portal.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
