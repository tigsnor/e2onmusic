package com.example.warproject.service;

import com.example.warproject.model.Music;
import com.example.warproject.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.print.Pageable;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Transactional
    public void save(Music music) {

        Music m = new Music();

        m.setMusic_name(music.getMusic_name());
        m.setMusic_file(music.getMusic_file());
        m.setMusic_ori_file(music.getMusic_ori_file());
        m.setMusic_url(music.getMusic_url());
        m.setImg_file(music.getImg_file());
        m.setImg_ori_file(music.getImg_ori_file());
        m.setImg_url(music.getImg_url());
        m.setAlbum_name(music.getAlbum_name());
        m.setDate(music.getDate());
        m.setGenre(music.getGenre());
        m.setSinger(music.getSinger());
        m.setAlbum_name(music.getAlbum_name());

        musicRepository.save(m);
    }

    @Transactional
    public Page<Music> pagingMusic(int page) {
        return musicRepository.findAll(PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
    }

    @Transactional
    public Page<Music> search(String musicSelect, String keyword, int page) {
        if (musicSelect.equals("musicname")) {
            Page<Music> MusicList = musicRepository.findByMusicnameContaining(keyword, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
            return MusicList;
        } else {
            Page<Music> ArtistList = musicRepository.findBySingerContaining(keyword, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
            return ArtistList;
        }
    }

}
