package com.example.musiclibrary.album;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Long id) {
        super("Album %d was not found".formatted(id));
    }
}
