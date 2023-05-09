package com.danest.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.danest.backend.domain.Education;

public interface EducationRepository extends CrudRepository<Education, Long> {
}
