package com.example.musiclibrary.album;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumResponse> findAlbums(
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String genre
    ) {
        return albumService.findAlbums(artist, genre);
    }

    @GetMapping("/{id}")
    public AlbumResponse getAlbum(@PathVariable Long id) {
        return albumService.getAlbum(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponse createAlbum(@Valid @RequestBody AlbumRequest request) {
        return albumService.createAlbum(request);
    }

    @PutMapping("/{id}")
    public AlbumResponse updateAlbum(@PathVariable Long id, @Valid @RequestBody AlbumRequest request) {
        return albumService.updateAlbum(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }
}
