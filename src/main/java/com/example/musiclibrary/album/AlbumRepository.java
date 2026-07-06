package com.example.musiclibrary.album;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistNameContainingIgnoreCase(String artistName);

    List<Album> findByGenreIgnoreCase(String genre);
}
