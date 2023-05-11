package com.danest.backend.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Institute {
    private String name;
    @Embedded
    private Image logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}
