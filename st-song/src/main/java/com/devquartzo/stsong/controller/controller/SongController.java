package com.devquartzo.stsong.controller.controller;

import com.devquartzo.stsong.controller.exception.SongNotFoundException;
import com.devquartzo.stsong.controller.model.Song;
import com.devquartzo.stsong.controller.repository.SongRepository;
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
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/songs")
    @ResponseBody
    public List<Song> getAllsongs() {
        return songRepository.findAll();
    }

    @GetMapping("/songs/{id}")
    @ResponseBody
    public Song getSong(@PathVariable String id) throws SongNotFoundException {

        Optional<Song> song = songRepository.findOneById(id);

        if (!song.isPresent())
            throw new SongNotFoundException("id-" + id);

        return song.get();
    }

    @PostMapping("/songs")
    public ResponseEntity<Object> createSong(@RequestBody Song song) {

        Song savedSong = songRepository.save(song);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSong.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("songs/{id}")
    public ResponseEntity<Object> updateSong(@RequestBody Song song, @PathVariable String id) {

        Optional<Song> songOptional = songRepository.findOneById(id);

        if (!songOptional.isPresent())
            return ResponseEntity.notFound().build();

        song.setId(id);

        songRepository.save(song);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @Modifying
    @DeleteMapping("/songs/{id}")
    public void deleteSong(@PathVariable String id) {
        songRepository.deleteById(id);
    }

}
