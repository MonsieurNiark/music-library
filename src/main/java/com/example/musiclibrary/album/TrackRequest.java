package com.example.musiclibrary.album;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TrackRequest(
        @NotBlank String title,
        @Min(1) int durationSeconds
) {
    public Track toTrack() {
        return new Track(title, durationSeconds);
    }
}
