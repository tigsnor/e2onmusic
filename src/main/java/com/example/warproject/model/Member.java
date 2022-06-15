package com.example.warproject.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Member {
    @Column(name = "seq")
    @Id
    @GeneratedValue
    private long seq;

    @Column(name = "id")
    @NotNull
    private String id;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "reg_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
