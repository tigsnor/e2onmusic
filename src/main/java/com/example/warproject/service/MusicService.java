package com.example.warproject.service;

import com.example.warproject.model.Music;
import com.example.warproject.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Transactional
    public void save(Music music) {

        System.out.println("--------------------------"+music.toString());
        musicRepository.save(music);
    }

    @Transactional
    public Page<Music> pagingMusic(int page) {
        return musicRepository.findAll(PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
    }

    @Transactional
    public Page<Music> search(String musicSelect, String keyword, int page) {
        if (musicSelect.equals("musicname")) {
            Page<Music> MusicList = musicRepository.findByMusicNameContaining(keyword, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
            return MusicList;
        } else {
            Page<Music> ArtistList = musicRepository.findBySingerContaining(keyword, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
            return ArtistList;
        }
    }

}
