package com.example.musiclibrary.album;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Track {
    @NotBlank
    @Column(nullable = false)
    private String title;

    @Min(1)
    @Column(nullable = false)
    private int durationSeconds;

    protected Track() {
    }

    public Track(String title, int durationSeconds) {
        this.title = title;
        this.durationSeconds = durationSeconds;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }
}
