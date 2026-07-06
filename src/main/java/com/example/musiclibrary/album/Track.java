package com.example.musiclibrary.album;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
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

    @Min(0)
    @Max(10)
    private Integer rating;

    protected Track() {
    }

    public Track(String title, int durationSeconds) {
        this(title, durationSeconds, null);
    }

    public Track(String title, int durationSeconds, Integer rating) {
        this.title = title;
        this.durationSeconds = durationSeconds;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public Integer getRating() {
        return rating;
    }
}
