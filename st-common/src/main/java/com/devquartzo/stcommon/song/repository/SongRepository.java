package com.devquartzo.stcommon.song.repository;

import com.devquartzo.stcommon.song.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findOneById(String id);

    @Modifying
    @Transactional
    @Query("delete from Song a where a.id = ?1")
    void deleteById(String id);
}
