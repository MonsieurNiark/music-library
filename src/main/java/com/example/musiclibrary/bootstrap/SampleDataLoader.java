package com.example.musiclibrary.bootstrap;

import com.example.musiclibrary.album.Album;
import com.example.musiclibrary.album.AlbumRepository;
import com.example.musiclibrary.album.Artist;
import com.example.musiclibrary.album.Track;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SampleDataLoader {
    @Bean
    CommandLineRunner loadSampleAlbums(AlbumRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }

            repository.saveAll(List.of(
                    new Album("Discovery", new Artist("Daft Punk", "France"), 2001, "Electronic", List.of(
                            new Track("One More Time", 320, 10),
                            new Track("Digital Love", 301, 9),
                            new Track("Harder, Better, Faster, Stronger", 224, 9)
                    )),
                    new Album("Kind of Blue", new Artist("Miles Davis", "United States"), 1959, "Jazz", List.of(
                            new Track("So What", 545, 10),
                            new Track("Freddie Freeloader", 589, 9),
                            new Track("Blue in Green", 337, 10)
                    )),
                    new Album("Blue", new Artist("Joni Mitchell", "Canada"), 1971, "Folk", List.of(
                            new Track("Carey", 181, 8),
                            new Track("California", 228, 9),
                            new Track("A Case of You", 262, 10)
                    ))
            ));
        };
    }
}
