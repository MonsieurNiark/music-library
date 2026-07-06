package com.example.musiclibrary.album;

public record TrackResponse(String title, int durationSeconds) {
    public static TrackResponse from(Track track) {
        return new TrackResponse(track.getTitle(), track.getDurationSeconds());
    }
}
