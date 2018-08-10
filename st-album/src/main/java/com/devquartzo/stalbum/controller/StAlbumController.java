package com.devquartzo.stalbum.controller;

import com.devquartzo.stalbum.exception.AlbumNotFoundException;
import com.devquartzo.stcommon.album.model.Album;
import com.devquartzo.stcommon.album.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class StAlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    @ResponseBody
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Album getAlbum(@PathVariable String id) throws AlbumNotFoundException {

        Optional<Album> albums = albumRepository.findOneById(id);

        if (!albums.isPresent())
            throw new AlbumNotFoundException("id-" + id);

        return albums.get();
    }

    @PostMapping
    public ResponseEntity<Object> createAlbum(@RequestBody Album album) {

        Album savedAlbum = albumRepository.save(album);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAlbum.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAlbum(@RequestBody Album album, @PathVariable String id) {

        Optional<Album> albumOptional = albumRepository.findOneById(id);

        if (!albumOptional.isPresent())
            return ResponseEntity.notFound().build();

        album.setId(id);

        albumRepository.save(album);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @Modifying
    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable String id) {
        albumRepository.deleteById(id);
    }

}
