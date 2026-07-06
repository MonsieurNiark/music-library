package com.example.musiclibrary.album;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void listsSeededAlbums() throws Exception {
        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(3)))
                .andExpect(jsonPath("$[0].tracks").isArray());
    }

    @Test
    void createsAlbum() throws Exception {
        String payload = """
                {
                  "title": "Random Access Memories",
                  "artistName": "Daft Punk",
                  "artistCountry": "France",
                  "releaseYear": 2013,
                  "genre": "Electronic",
                  "tracks": [
                    { "title": "Give Life Back to Music", "durationSeconds": 275, "rating": 9 },
                    { "title": "Instant Crush", "durationSeconds": 337, "rating": 10 }
                  ]
                }
                """;

        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Random Access Memories"))
                .andExpect(jsonPath("$.tracks[0].rating").value(9))
                .andExpect(jsonPath("$.tracks[1].rating").value(10))
                .andExpect(jsonPath("$.totalDurationSeconds").value(612));
    }

    @Test
    void createsAlbumWithUnratedTrack() throws Exception {
        String payload = """
                {
                  "title": "Unrated Sessions",
                  "artistName": "Example Artist",
                  "artistCountry": "France",
                  "releaseYear": 2024,
                  "genre": "Ambient",
                  "tracks": [
                    { "title": "Quiet Start", "durationSeconds": 180 }
                  ]
                }
                """;

        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tracks[0].title").value("Quiet Start"))
                .andExpect(jsonPath("$.tracks[0].rating").doesNotExist());
    }

    @Test
    void rejectsTrackRatingOutsideAcceptedRange() throws Exception {
        String payload = """
                {
                  "title": "Too Much",
                  "artistName": "Example Artist",
                  "artistCountry": "France",
                  "releaseYear": 2024,
                  "genre": "Rock",
                  "tracks": [
                    { "title": "Loud Ending", "durationSeconds": 240, "rating": 11 }
                  ]
                }
                """;

        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details[0]", containsString("rating")));
    }

    @Test
    void validatesAlbumPayload() throws Exception {
        String payload = """
                {
                  "title": "",
                  "artistName": "",
                  "releaseYear": 1800,
                  "genre": "",
                  "tracks": []
                }
                """;

        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details[0]", containsString("must")));
    }

    @Test
    void returnsNotFoundWhenDeletingMissingAlbum() throws Exception {
        mockMvc.perform(delete("/api/albums/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Album 9999 was not found"));
    }
}
