package com.example.warproject.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Music {
    @Id
    @GeneratedValue
    private Long idx;

    @NotNull
    private String musicfile;

    private String musicorifile;

    private String musicurl;

    private String imgfile;

    private String imgorifile;

    private String imgurl;

    @NotNull
    private String musicname;

    @NotNull
    private String albumname;

    @NotNull
    private String singer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private String genre;
}
