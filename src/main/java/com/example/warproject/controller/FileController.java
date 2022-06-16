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
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class FileController {

    @GetMapping(value="/download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String fileName = request.getParameter("filename");
        String fileOriName = request.getParameter("fileoriname");
        System.out.println(fileOriName);
        String path = "C:/musicsource/" + fileName;

        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        String encoding = URLEncoder.encode(
                fileOriName, "UTF-8").replace("+", "%20");

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName="+encoding);
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }


    @GetMapping(value="/play/{filename}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> play(@PathVariable("filename") String fileName){
        String path = "C:/musicsource/";

        Resource resource = new FileSystemResource(path + fileName);

        if(!resource.exists()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        Path filePath = null;

        try{
            filePath = Paths.get(path + fileName);
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
