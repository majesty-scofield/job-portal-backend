package com.backend.job_portal.api;

import com.backend.job_portal.dtos.ProfileDto;
import com.backend.job_portal.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin
@RequiredArgsConstructor
public class ProfileApi {
    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto request) {
        return ResponseEntity.ok(profileService.updateProfile(request));
    }

    @DeleteMapping("/destroy/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }
}
