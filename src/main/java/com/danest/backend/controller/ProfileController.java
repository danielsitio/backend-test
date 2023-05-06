package com.danest.backend.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danest.backend.domain.Image;
import com.danest.backend.repository.UserRepository;
import com.danest.backend.service.StorageService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class ProfileController {

    private final StorageService storageService;
    private final UserRepository userRepository;

    ProfileController(StorageService storageService, UserRepository userRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    @Transactional
    @PostMapping("/picture")
    void saveProfilePicture(@RequestParam MultipartFile profilePicture) throws Exception {
        Image currentProfilePicture = this.userRepository.findById(Long.valueOf("1")).get().getProfile().getPicture();
        if (currentProfilePicture != null) {
            this.storageService.delete(currentProfilePicture.getName());
            this.storageService.store(profilePicture);
        }
        String localUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        Image newProfilePicture = new Image();
        newProfilePicture.setName(profilePicture.getOriginalFilename());
        newProfilePicture.setUrl(localUrl + "/images/" + profilePicture.getOriginalFilename());
        this.userRepository.findById(Long.valueOf("1")).get().getProfile()
                .setPicture(newProfilePicture);
    }

    @GetMapping("/picture")
    String getPictureUrl() {
        String url = null;
        if (this.userRepository.findById(Long.valueOf("1")).get().getProfile().getPicture() != null)
            url = this.userRepository.findById(Long.valueOf("1")).get().getProfile().getPicture().getUrl();
        if (url != null)
            return url;
        return null;
    }
}
