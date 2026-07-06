package com.example.musiclibrary.album;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional(readOnly = true)
    public List<AlbumResponse> findAlbums(String artist, String genre) {
        if (artist != null && !artist.isBlank()) {
            return albumRepository.findByArtistNameContainingIgnoreCase(artist).stream()
                    .map(AlbumResponse::from)
                    .toList();
        }

        if (genre != null && !genre.isBlank()) {
            return albumRepository.findByGenreIgnoreCase(genre).stream()
                    .map(AlbumResponse::from)
                    .toList();
        }

        return albumRepository.findAll().stream().map(AlbumResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public AlbumResponse getAlbum(Long id) {
        return albumRepository.findById(id)
                .map(AlbumResponse::from)
                .orElseThrow(() -> new AlbumNotFoundException(id));
    }

    public AlbumResponse createAlbum(AlbumRequest request) {
        return AlbumResponse.from(albumRepository.save(request.toAlbum()));
    }

    public AlbumResponse updateAlbum(Long id, AlbumRequest request) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
        Album updated = request.toAlbum();
        album.update(updated.getTitle(), updated.getArtist(), updated.getReleaseYear(), updated.getGenre(), updated.getTracks());
        return AlbumResponse.from(album);
    }

    public void deleteAlbum(Long id) {
        if (!albumRepository.existsById(id)) {
            throw new AlbumNotFoundException(id);
        }
        albumRepository.deleteById(id);
    }
}
