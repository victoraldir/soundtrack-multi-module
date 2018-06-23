package com.devquartzo.stcommon.song.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    private String artistId;

    @JsonProperty
    private String albumId;

    @JsonProperty
    private String urlVideo;

    public Song() {
        id = UUID.randomUUID().toString();
    }
}
