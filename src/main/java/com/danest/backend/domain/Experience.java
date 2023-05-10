package com.danest.backend.domain;

import java.time.LocalDate;
import java.util.Map;

import com.danest.backend.util.NullOrNotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "experiences")
public class Experience {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String position;
    @NotBlank
    private String description;
    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;
    @NotNull
    @Column(nullable = false)
    private LocalDate finishDate;
    @NullOrNotBlank
    private String logo;

    public Long getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void updateFromMap(Map<String, String> partialExperience) {
        partialExperience.keySet()
                .stream()
                .forEach(fieldToChange -> {
                    switch (fieldToChange) {
                        case "position":
                            setPosition(partialExperience.get(fieldToChange));
                            break;
                        case "description":
                            setDescription(partialExperience.get(fieldToChange));
                            break;
                        case "startDate":
                            setStartDate(LocalDate.parse(partialExperience.get(fieldToChange)));
                            break;
                        case "finishDate":
                            setFinishDate(LocalDate.parse(partialExperience.get(fieldToChange)));
                            break;
                    }
                });
    }
}
