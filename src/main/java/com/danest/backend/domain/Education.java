package com.danest.backend.domain;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
    @Column(nullable = false)
    private String title;
    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;
    @NotNull
    @Column(nullable = false)
    private LocalDate finishDate;
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "school_name", nullable = false))
    @AttributeOverride(name = "logo.url", column = @Column(name = "school_logo"))
    private Institute school;

    public Long getId() {
        return id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Institute getSchool() {
        return school;
    }

    public void setSchool(Institute school) {
        this.school = school;
    }

    public void copyEducation(Education education) {
        this.school = education.school;
        this.title = education.title;
        this.startDate = education.startDate;
        this.finishDate = education.finishDate;
    }

    public void updateFromMap(Map<String, String> partialEducation) {
        partialEducation.keySet()
                .stream()
                .forEach(fieldToChange -> {
                    switch (fieldToChange) {
                        case "title":
                            setTitle(partialEducation.get(fieldToChange));
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