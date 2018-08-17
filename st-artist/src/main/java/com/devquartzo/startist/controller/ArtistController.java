package com.devquartzo.startist.controller;

import com.devquartzo.startist.exception.ArtistNotFoundException;
import com.devquartzo.stcommon.artist.model.Artist;
import com.devquartzo.stcommon.artist.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secured/artists")
public class ArtistController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping
    @ResponseBody
    public List<Artist> getAllArtists() {

        System.out.println(discoveryClient.getServices().size());

        return artistRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Artist getArtist(@PathVariable String id) throws ArtistNotFoundException {

        Optional<Artist> artist = artistRepository.findOneById(id);

        if (!artist.isPresent())
            throw new ArtistNotFoundException("id-" + id);

        return artist.get();
    }

    @PostMapping
    public ResponseEntity<Object> createArtist(@RequestBody Artist artist) {

        Artist savedArtist = artistRepository.save(artist);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedArtist.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArtist(@RequestBody Artist artist, @PathVariable String id) {

        Optional<Artist> artistOptional = artistRepository.findOneById(id);

        if (!artistOptional.isPresent())
            return ResponseEntity.notFound().build();

        artist.setId(id);

        artistRepository.save(artist);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @Modifying
    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable String id) {
        artistRepository.deleteArtistById(id);
    }
}
