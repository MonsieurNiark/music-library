package com.example.musiclibrary.album;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Artist {
    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    protected Artist() {
    }

    public Artist(String name, String country) {
        this.name = name;
        this.country = country == null || country.isBlank() ? "Unknown" : country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
