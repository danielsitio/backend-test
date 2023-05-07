package com.danest.backend.domain;

import java.util.Map;
import java.util.Optional;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Profile {
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    @NotBlank
    @Column(nullable = false)
    private String title;
    @NotBlank
    @Column(nullable = false, length = 1024)
    private String description;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "profile_image"))
    private Image image;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "banner_image"))
    private Image bannerImage;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Optional<Image> getPicture() {
        return Optional.ofNullable(image);
    }

    public Optional<Image> getBannerImage() {
        return Optional.ofNullable(bannerImage);
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(Image image) {
        this.image = image;
    }

    public void setBannerImage(Image bannerImage) {
        this.bannerImage = bannerImage;
    }

    public void updateFromMap(Map<String, String> partialProfile) {
        partialProfile.keySet()
                .stream()
                .forEach(fieldToChange -> {
                    switch (fieldToChange) {
                        case "name":
                            setName(partialProfile.get(fieldToChange));
                            break;
                        case "lastName":
                            setLastName(partialProfile.get(fieldToChange));
                            break;
                        case "title":
                            setTitle(partialProfile.get(fieldToChange));
                            break;
                        case "description":
                            setDescription(partialProfile.get(fieldToChange));
                            break;
                    }
                });
    }
}
