package com.danest.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.danest.backend.domain.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {

}
