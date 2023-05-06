package com.danest.backend.domain;

import com.danest.backend.util.NullOrNotBlank;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Profile {
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String description;
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "image_name"))
    @AttributeOverride(name = "url", column = @Column(name = "image_url"))
    private Image image;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public Image getPicture() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(Image image) {
        this.image = image;
    }
}
