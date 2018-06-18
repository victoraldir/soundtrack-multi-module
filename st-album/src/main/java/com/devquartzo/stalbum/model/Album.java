package com.devquartzo.stalbum.model;

import com.devquartzo.stalbum.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Album {

    @Id
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String coverUrl;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> songsId;

    public Album(){
        id = UUID.randomUUID().toString();
    }

}
