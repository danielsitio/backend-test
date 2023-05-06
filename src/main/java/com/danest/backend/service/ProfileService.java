package com.danest.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danest.backend.domain.Image;
import com.danest.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {
    private final StorageService storageService;
    private final UserRepository userRepository;

    ProfileService(StorageService storageService, UserRepository userRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void changeProfilePicture(MultipartFile newProfilePicture) throws Exception {
        if (thereIsAlreadyAProfilePicture())
            deleteCurrentProfilePicture();
        storageService.store(newProfilePicture);
        Image picture = new Image(localUrl() + "/images/" + newProfilePicture.getOriginalFilename());
        userRepository.findOnlyUser().getProfile().setPicture(picture);
    }

    public String getProfilePictureUrl() {
        if (thereIsAlreadyAProfilePicture())
            return currentProfilePicture().get().getUrl();
        return null;
    }

    private void deleteCurrentProfilePicture() {
        this.storageService.delete(currentProfilePicture().get().getName());
    }

    private boolean thereIsAlreadyAProfilePicture() {
        return this.currentProfilePicture().isPresent();
    }

    private Optional<Image> currentProfilePicture() {
        return this.userRepository.findOnlyUserProfile().getPicture();
    }

    private String localUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

}
