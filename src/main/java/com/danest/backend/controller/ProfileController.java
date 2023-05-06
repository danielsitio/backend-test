package com.danest.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin

public class ProfileController {

    private final ProfileService profileService;

    ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/picture")
    void saveProfilePicture(@RequestParam MultipartFile profilePicture) throws Exception {
        profileService.changeProfilePicture(profilePicture);
    }

    @GetMapping("/picture")
    String getPictureUrl() {
        return profileService.getProfilePictureUrl();
    }
}
