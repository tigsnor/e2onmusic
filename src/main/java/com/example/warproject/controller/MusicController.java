package com.example.warproject.controller;

import com.example.warproject.model.Music;
import com.example.warproject.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.security.Principal;
import java.util.Iterator;


@Slf4j
@Controller
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping("/upload")
    public String insert(MultipartHttpServletRequest request, Music m) throws Exception{
        Iterator<String> files = null;
        files = request.getFileNames();

        while (files.hasNext()) {
            String formName = files.next();
            MultipartFile formFile = request.getFile(formName);
            String sourceFileName = formFile.getOriginalFilename();
            String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
            File destinationFile;
            String destinationFileName;
            String mp3FileUrl = "C:/musicsource/";
            String jpgFileUrl = "C:/imagesource/";
            System.out.println("----------"+formName);

            if(sourceFileNameExtension.equals("mp3")){
                do {
                    destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
                    destinationFile = new File(mp3FileUrl + destinationFileName);
                } while (destinationFile.exists());

                destinationFile.getParentFile().mkdirs();
                formFile.transferTo(destinationFile);

                m.setMusicFile(destinationFileName);
                m.setMusicOriFile(sourceFileName);
                m.setMusicUrl(mp3FileUrl);
            }
            else {
                do {
                    destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
                    destinationFile = new File(jpgFileUrl + destinationFileName);
                } while (destinationFile.exists());

                destinationFile.getParentFile().mkdirs();
                formFile.transferTo(destinationFile);

                m.setImgFile(destinationFileName);
                m.setImgOriFile(sourceFileName);
                m.setImgUrl(jpgFileUrl);
            }
        }
        System.out.println("--------------------????????????"+m.toString());
        musicService.save(m);

        return "redirect:/";
    }

    //???????????????
    @RequestMapping(value = {"/","board"})
    public String paging(HttpSession session, Model model, Principal principal, @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        Page<Music> listPage = musicService.pagingMusic(page);

        int totalPage = listPage.getTotalPages();

        if(!(principal == null)){
            model.addAttribute("username",principal.getName());
        }else{
            model.addAttribute("username","1");
        }
        model.addAttribute("board", listPage.getContent());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNo", page);

        return "board";
    }

    //??????
    @RequestMapping("/search")
    public String search(String musicselect, String keyword, Model model,  @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        Page<Music> searchList = musicService.search(musicselect, keyword, page);

        int totalPage = searchList.getTotalPages();


        model.addAttribute("searchList", searchList.getContent());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNo", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("musicSelect", musicselect);

        return "boardsearch";
    }

    //RestController???

}
