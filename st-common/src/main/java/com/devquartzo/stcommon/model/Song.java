package com.devquartzo.stcommon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Time;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Song {

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
