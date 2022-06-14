package com.example.warproject.service;

import com.example.warproject.model.Board;
import com.example.warproject.model.Music;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MusicService {

    void save(Music music);

    Page<Music> pagingMusic(int page);

    Page<Music> search(String musicselect, String keyword, int page);
}
