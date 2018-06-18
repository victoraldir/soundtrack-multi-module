package com.devquartzo.stcommon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Album {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String coverUrl;

    private List<String> songsId;

    public Album(){
        id = UUID.randomUUID().toString();
    }

}
