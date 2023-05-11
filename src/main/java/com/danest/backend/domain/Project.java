package com.danest.backend.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import com.danest.backend.util.NullOrNotBlank;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "portrait_url"))
    private Image portrait;

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

    public Optional<Image> getPortrait() {
        return Optional.ofNullable(portrait);
    }

    public void setPortrait(Image portrait) {
        this.portrait = portrait;
    }

    public void copyProject(Project project) {
        setName(project.name);
        setDescription(project.description);
        setLink(project.link);
        setRealizationDate(project.realizationDate);
        setPortrait(project.portrait);
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
