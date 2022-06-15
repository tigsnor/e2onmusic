package com.example.warproject.repositories;

import com.example.warproject.model.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends CrudRepository<Music, Long> {
    Page<Music> findAll(Pageable pageable);

    Page<Music> findByMusicNameContaining(String keyword, PageRequest pageable);
    Page<Music> findBySingerContaining(String keyword, PageRequest pageable);
}
