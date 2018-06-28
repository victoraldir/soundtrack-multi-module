package com.devquartzo.stcommon.song.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Song {

    @Id
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Time duration;

    @JsonProperty
    @Column(name = "artist_id")
    private String artistId;

    @JsonProperty
    @Column(name = "album_id")
    private String albumId;

    @JsonProperty
    @Column(name = "url_video")
    private String urlVideo;

    public Song() {
        id = UUID.randomUUID().toString();
    }
}
