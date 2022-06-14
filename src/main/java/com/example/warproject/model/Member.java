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
    @Id
    @GeneratedValue
    private long seq;

    @NotNull
    private String id;
    @NotNull
    private String password;

    private String name;

    @Column(name = "RegDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Member(@NotNull String id, @NotNull String password, String name, int admin) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.date = LocalDate.now();
    }
}
