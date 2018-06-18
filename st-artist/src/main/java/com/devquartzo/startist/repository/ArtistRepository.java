package com.devquartzo.startist.repository;


import com.devquartzo.startist.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findOneById(String id);

    @Modifying
    @Transactional
    @Query("delete from Artist a where a.id = ?1")
    void deleteArtistById(String id);

}
