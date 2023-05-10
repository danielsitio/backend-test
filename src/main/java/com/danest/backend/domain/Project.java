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

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate realizationDate;
    @NullOrNotBlank
    private String link;
    @NullOrNotBlank
    private String image;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(LocalDate realizationDate) {
        this.realizationDate = realizationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void updateFromMap(Map<String, String> partialProject) {
        partialProject.keySet()
                .stream()
                .forEach(fieldToChange -> {
                    switch (fieldToChange) {
                        case "name":
                            setName(partialProject.get(fieldToChange));
                            break;
                        case "description":
                            setDescription(partialProject.get(fieldToChange));
                            break;
                        case "realizationDate":
                            setRealizationDate(LocalDate.parse(partialProject.get(fieldToChange)));
                            break;
                        case "link":
                            setLink(partialProject.get(fieldToChange));
                            break;
                    }
                });
    }

}
