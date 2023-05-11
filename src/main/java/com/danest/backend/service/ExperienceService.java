package com.danest.backend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.danest.backend.domain.Experience;
import com.danest.backend.domain.Image;
import com.danest.backend.repository.ExperienceRepository;

import jakarta.transaction.Transactional;

@Service
public class ExperienceService {

    @Value("${custom.image-directory}")
    private String imageDirectory;

    private final ExperienceRepository experienceRepository;
    private final StorageService storageService;

    ExperienceService(ExperienceRepository experienceRepository, StorageService storageService) {
        this.experienceRepository = experienceRepository;
        this.storageService = storageService;
    }

    public Experience getExperience(Long id) {
        return this.experienceRepository.findById(id).get();
    }

    public void saveExperience(Experience newExperience, MultipartFile workplaceLogo) throws Exception {
        if (workplaceLogo != null) {
            storageService.store(workplaceLogo);
            Image image = new Image(imageDirectory + workplaceLogo.getOriginalFilename());
            newExperience.getWorkplace().setLogo(image);
        }
        this.experienceRepository.save(newExperience);
    }

    public Iterable<Experience> getAllExperiences() {
        return this.experienceRepository.findAll();
    }

    public void deleteExperience(Long id) {
        this.experienceRepository.deleteById(id);
    }

    @Transactional
    public void updateExperience(Long id, Map<String, String> partialExperience) {
        this.experienceRepository.findById(id).get().updateFromMap(partialExperience);
    }
}
