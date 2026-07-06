package com.example.musiclibrary.album;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Valid
    @Embedded
    private Artist artist;

    @Min(1900)
    @Max(2100)
    @Column(nullable = false)
    private int releaseYear;

    @NotBlank
    @Column(nullable = false)
    private String genre;

    @NotEmpty
    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "album_tracks", joinColumns = @JoinColumn(name = "album_id"))
    private List<Track> tracks = new ArrayList<>();

    protected Album() {
    }

    public Album(String title, Artist artist, int releaseYear, String genre, List<Track> tracks) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.tracks = new ArrayList<>(tracks);
    }

    public void update(String title, Artist artist, int releaseYear, String genre, List<Track> tracks) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.tracks = new ArrayList<>(tracks);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public List<Track> getTracks() {
        return List.copyOf(tracks);
    }

    public int getTotalDurationSeconds() {
        return tracks.stream().mapToInt(Track::getDurationSeconds).sum();
    }

    public boolean isClassic() {
        return releaseYear <= Year.now().minusYears(25).getValue();
    }
}
