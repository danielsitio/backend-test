package com.danest.backend.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.danest.backend.domain.Education;
import com.danest.backend.repository.EducationRepository;

import jakarta.transaction.Transactional;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Education getEducation(Long id) {
        return this.educationRepository.findById(id).get();
    }

    public void saveEducation(Education newEducation) {
        this.educationRepository.save(newEducation);
    }

    public Iterable<Education> getAllEducations() {
        return this.educationRepository.findAll();
    }

    public void deleteEducation(Long id) {
        this.educationRepository.deleteById(id);
    }

    @Transactional
    public void modifyEducation(Long id, Map<String, String> partialEducation) {
        this.educationRepository.findById(id).get().updateFromMap(partialEducation);
    }
}
