package com.devquartzo.stcommon.album.repository;

import com.devquartzo.stcommon.album.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findOneById(String id);

    @Modifying
    @Transactional
    @Query("delete from Album a where a.id = ?1")
    void deleteById(String id);

}
