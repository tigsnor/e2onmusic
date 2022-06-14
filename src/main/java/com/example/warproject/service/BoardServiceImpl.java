package com.example.warproject.service;



import com.example.warproject.model.Board;
import com.example.warproject.repositories.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.OrderBy;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void insertBoard(Board board) {
        boardRepository.save(board);

    }

    @Override
    public Board getBoard(Board board) {
        Board findBoard = boardRepository.findById(board.getBoardno()).get();
        boardRepository.save(findBoard);
        return findBoard;
    }

    @Override
    public void updateBoard(Board board) {
        // 수정 대상 글을 가져온다
        Board findBoard = boardRepository.findById(board.getBoardno()).get();

        // 가져온 글에 수정한 내용을 세팅한다
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());

        // DB에 저장
        boardRepository.save(findBoard);
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getBoardno());

    }


}
