package com.danest.backend.service;

import org.springframework.stereotype.Service;

import com.danest.backend.domain.Education;
import com.danest.backend.repository.EducationRepository;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public void saveEducation(Education newEducation) {
        this.educationRepository.save(newEducation);
    }

    public Iterable<Education> getAllEducations() {
        return this.educationRepository.findAll();
    }
}
