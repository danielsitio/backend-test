package com.danest.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProjectDto {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
