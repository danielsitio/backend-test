package com.danest.backend.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Image {
    private String url;

    public Image() {
    }

    public Image(String url) {
        this.url = url;
    }

    public String getName() {
        int index = url.lastIndexOf("/");
        return url.substring(index);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
