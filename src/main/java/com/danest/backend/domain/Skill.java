package com.danest.backend.domain;

import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private int level;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateFromMap(Map<String, String> partialSkill) {
        partialSkill.keySet().stream().forEach(fieldToChange -> {
            switch (fieldToChange) {
                case "name":
                    setName(partialSkill.get(fieldToChange));
                    break;
                case "level":
                    setLevel(Integer.parseInt(partialSkill.get(fieldToChange)));
                    break;
            }
        });
    }
}
