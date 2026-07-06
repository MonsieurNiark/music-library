package com.example.musiclibrary.album;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AlbumRequest(
        @NotBlank String title,
        @NotBlank String artistName,
        String artistCountry,
        @Min(1900) @Max(2100) int releaseYear,
        @NotBlank String genre,
        @NotEmpty @Valid List<TrackRequest> tracks
) {
    public Album toAlbum() {
        return new Album(
                title,
                new Artist(artistName, artistCountry),
                releaseYear,
                genre,
                tracks.stream().map(TrackRequest::toTrack).toList()
        );
    }
}
