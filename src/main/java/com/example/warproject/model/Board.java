package com.example.warproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long boardno;
    private String title;
    private String content;

    @Column(updatable = false)
    private String writer;

}
