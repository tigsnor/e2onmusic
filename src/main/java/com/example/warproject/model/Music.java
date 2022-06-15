package com.example.warproject.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Music {
    @Column(name = "idx")
    @Id
    @GeneratedValue
    private Long idx;

    @Column(name = "musicfile")
    @NotNull
    private String musicFile;

    @Column(name = "musicorifile")
    private String musicOriFile;

    @Column(name = "musicurl")
    private String musicUrl;

    @Column(name = "imgfile")
    private String imgFile;

    @Column(name = "imgorifile")
    private String imgOriFile;

    @Column(name = "imgurl")
    private String imgUrl;

    @Column(name = "musicname")
    @NotNull
    private String musicName;

    @Column(name = "albumname")
    @NotNull
    private String albumName;

    @Column(name = "singer")
    @NotNull
    private String singer;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "genre")
    @NotNull
    private String genre;
}
