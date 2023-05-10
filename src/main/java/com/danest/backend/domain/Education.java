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
@Table(name = "educations")
public class Education {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String institute;
    @NotBlank
    private String title;
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

    public String getInstitute() {
        return institute;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public void updateFromMap(Map<String, String> partialEducation) {
        partialEducation.keySet()
                .stream()
                .forEach(fieldToChange -> {
                    switch (fieldToChange) {
                        case "title":
                            setTitle(partialEducation.get(fieldToChange));
                            break;
                        case "institute":
                            setInstitute(partialEducation.get(fieldToChange));
                            break;
                        case "startDate":
                            setStartDate(LocalDate.parse(partialEducation.get(fieldToChange)));
                            break;
                        case "finishDate":
                            setFinishDate(LocalDate.parse(partialEducation.get(fieldToChange)));
                            break;
                    }
                });
    }
}