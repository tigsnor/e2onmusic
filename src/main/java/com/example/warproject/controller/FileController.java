package com.example.warproject.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class FileController {

    @GetMapping(value="/download")
    public void download(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {

        String filename = request.getParameter("filename");
        String fileoriname = request.getParameter("fileoriname");
        System.out.println(fileoriname);
        String path = "C:/musicsource/" + filename;

        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        String Encoding = URLEncoder.encode(
                fileoriname, "UTF-8").replace("+", "%20");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName="+Encoding);
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }


    @GetMapping(value="/play/{filename}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> play(@PathVariable("filename") String filename){
//        String filename = request.getParameter("filename");
        String path = "C:/musicsource/";

        Resource resource = new FileSystemResource(path + filename);

        if(!resource.exists()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        Path filePath = null;

        try{
            filePath = Paths.get(path + filename);
            headers.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

    @GetMapping(value="/picture/{filename}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> picture(@PathVariable("filename") String filename){
//        String filename = request.getParameter("filename");
        String path = "C:/imagesource/";

        Resource resource = new FileSystemResource(path + filename);

        if(!resource.exists()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        Path filePath = null;

        try{
            filePath = Paths.get(path + filename);
            headers.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

}
