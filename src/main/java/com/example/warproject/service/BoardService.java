package com.example.warproject.service;

import com.example.warproject.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

//서비스 인터페이스
public interface BoardService {

    void insertBoard(Board board);

    Board getBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(Board board);
}
