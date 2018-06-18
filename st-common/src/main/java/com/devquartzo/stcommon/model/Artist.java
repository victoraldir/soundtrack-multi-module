package com.devquartzo.stcommon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Artist {


    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    public Artist() {
        id = UUID.randomUUID().toString();
    }
}
