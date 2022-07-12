package com.example.warproject.service;

import com.example.warproject.model.Music;
import com.example.warproject.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

//    public List<Music> getMusic(Music music){
//        List<Music> musicBoard = musicRepository.findAll()
//    }

    @Transactional
    public void save(Music music) {
        musicRepository.save(music);
    }

    @Transactional
    public Page<Music> pagingMusic(int page) {
        return musicRepository.findAll(PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "idx")));
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
