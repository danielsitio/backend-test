package com.danest.backend.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Profile;
import com.danest.backend.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin

public class ProfileController {

    private Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final ProfileService profileService;

    ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    Profile getProfile() {
        return profileService.getProfile();
    }

    @PatchMapping
    void patchProfile(@RequestBody Map<String, String> partialProfile) {
        profileService.patchProfile(partialProfile);
    }

    @PostMapping("/picture")
    void saveProfilePicture(@RequestParam MultipartFile profilePicture) throws Exception {
        profileService.changeProfilePicture(profilePicture);
    }

    @PostMapping("/banner")
    void saveBannerImage(@RequestParam MultipartFile bannerImage) throws Exception {
        profileService.changeBannerImage(bannerImage);
    }

}
