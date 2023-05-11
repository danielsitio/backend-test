package com.danest.backend.service;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danest.backend.domain.Image;
import com.danest.backend.domain.Profile;
import com.danest.backend.domain.User;
import com.danest.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {

    @Value("${custom.image-directory-url}")
    private String imageDirectory;

    Logger logger = LoggerFactory.getLogger(ProfileService.class);

    private final StorageService storageService;

    private final UserRepository userRepository;

    ProfileService(StorageService storageService, UserRepository userRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    public Profile getProfile() {
        return onlyUser().getProfile();
    }

    @Transactional
    public void patchProfile(Map<String, String> partialProfile) {
        onlyUser().getProfile().updateFromMap(partialProfile);
    }

    @Transactional
    public void changeProfilePicture(MultipartFile newProfilePicture) throws Exception {
        if (thereIsAlreadyAProfilePicture())
            deleteCurrentProfilePicture();
        storageService.store(newProfilePicture, newProfilePicture.getName());
        Image picture = new Image(localUrl() + "/images/" + newProfilePicture.getOriginalFilename());
        onlyUser().getProfile().setPicture(picture);
    }

    @Transactional
    public void changeBannerImage(MultipartFile newBannerImage) throws Exception {
        if (thereIsAlreadyABannerImage())
            deleteCurrentBannerImage();
        storageService.store(newBannerImage, newBannerImage.getName());
        Image bannerImage = new Image(imageDirectory + newBannerImage.getOriginalFilename());
        onlyUser().getProfile().setBannerImage(bannerImage);
    }

    private void deleteCurrentProfilePicture() {
        this.storageService.delete(currentProfilePicture().get().getName());
    }

    private void deleteCurrentBannerImage() {
        this.storageService.delete(currentBannerImage().get().getName());
    }

    private boolean thereIsAlreadyAProfilePicture() {
        return this.currentProfilePicture().isPresent();
    }

    private boolean thereIsAlreadyABannerImage() {
        return this.currentProfilePicture().isPresent();
    }

    private Optional<Image> currentProfilePicture() {
        return this.onlyUser().getProfile().getPicture();
    }

    private Optional<Image> currentBannerImage() {
        return this.onlyUser().getProfile().getBannerImage();
    }

    private String localUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    private User onlyUser() {
        return this.userRepository.findOnlyUser();
    }

}
