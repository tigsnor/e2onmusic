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
    private String music_file;

    @Column(name = "musicorifile")
    private String music_ori_file;

    @Column(name = "musicurl")
    private String music_url;

    @Column(name = "imgfile")
    private String img_file;

    @Column(name = "imgorifile")
    private String img_ori_file;

    @Column(name = "imgurl")
    private String img_url;

    @Column(name = "musicname")
    @NotNull
    private String music_name;

    @Column(name = "albumname")
    @NotNull
    private String album_name;

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
