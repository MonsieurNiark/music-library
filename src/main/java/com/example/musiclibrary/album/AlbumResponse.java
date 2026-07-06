package com.example.musiclibrary.album;

import java.util.List;

public record AlbumResponse(
        Long id,
        String title,
        String artistName,
        String artistCountry,
        int releaseYear,
        String genre,
        List<TrackResponse> tracks,
        int totalDurationSeconds,
        boolean classic
) {
    public static AlbumResponse from(Album album) {
        return new AlbumResponse(
                album.getId(),
                album.getTitle(),
                album.getArtist().getName(),
                album.getArtist().getCountry(),
                album.getReleaseYear(),
                album.getGenre(),
                album.getTracks().stream().map(TrackResponse::from).toList(),
                album.getTotalDurationSeconds(),
                album.isClassic()
        );
    }
}
