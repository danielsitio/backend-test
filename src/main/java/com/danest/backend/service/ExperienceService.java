package com.danest.backend.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.danest.backend.domain.Experience;
import com.danest.backend.repository.ExperienceRepository;

import jakarta.transaction.Transactional;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Experience getExperience(Long id) {
        return this.experienceRepository.findById(id).get();
    }

    public void saveExperience(Experience newExperience) {
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
