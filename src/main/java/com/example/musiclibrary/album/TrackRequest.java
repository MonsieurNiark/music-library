package com.example.musiclibrary.album;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TrackRequest(
        @NotBlank String title,
        @Min(1) int durationSeconds,
        @Min(0) @Max(10) Integer rating
) {
    public Track toTrack() {
        return new Track(title, durationSeconds, rating);
    }
}
