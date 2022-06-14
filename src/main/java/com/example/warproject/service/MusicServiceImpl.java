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
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public void save(Music music) {

        Music m = new Music();

        m.setMusicname(music.getMusicname());
        m.setMusicfile(music.getMusicfile());
        m.setMusicorifile(music.getMusicorifile());
        m.setMusicurl(music.getMusicurl());
        m.setImgfile(music.getImgfile());
        m.setImgorifile(music.getImgorifile());
        m.setImgurl(music.getImgurl());
        m.setAlbumname(music.getAlbumname());
        m.setDate(music.getDate());
        m.setGenre(music.getGenre());
        m.setSinger(music.getSinger());
        m.setAlbumname(music.getAlbumname());

        musicRepository.save(m);
    }

    @Transactional
    public Page<Music> pagingMusic(int page) {
        return musicRepository.findAll(PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
    }

    @Transactional
    public Page<Music> search(String musicselect, String keyword, int page) {
        if (musicselect.equals("musicname")) {
            Page<Music> MusicList = musicRepository.findByMusicnameContaining(keyword, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
            return MusicList;
        } else {
            Page<Music> ArtistList = musicRepository.findBySingerContaining(keyword, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "idx")));
            return ArtistList;
        }
    }

}
