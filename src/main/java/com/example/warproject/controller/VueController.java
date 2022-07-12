package com.example.warproject.controller;

import com.example.warproject.model.Member;
import com.example.warproject.model.Music;
import com.example.warproject.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VueController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/board")
    public Page<Music> paging(Model model, Principal principal, @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        Page<Music> listPage = musicService.pagingMusic(page);

        return listPage;
    }

//    @GetMapping("/vueboard")
//    public Map<String, Object> vueBoard(Music music){
//        Map<String, Object> result = new HashMap<>();
//        musicService.getBoard
//        if(member == null){
//            result.put("result", false);
//        }
//        else{
//            result.put("result", true);
//            result.put("member", member);
//        }
//        return result;
//
//    }

    @RequestMapping("/search")
    public Page<Music> search(String musicselect, String keyword, Model model,  @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        Page<Music> searchList = musicService.search(musicselect, keyword, page);

        return searchList;
    }
}
