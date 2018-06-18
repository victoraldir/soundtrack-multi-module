package com.devquartzo.startist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Artist {

    @Id
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
