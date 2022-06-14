package com.example.warproject.controller;

import com.example.warproject.model.Board;
import com.example.warproject.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/main")
    public String main(){
        return "index";
    }

    /*
        글쓰기화면
        @return
     */
    @RequestMapping("/top")
    public String top(){
        return "top";
    }

    @RequestMapping("/write")
    public String insertBoardView(){
        return "write";
    }

    /*
        글쓰기 처리
        @param board
        @return
     */
    @RequestMapping("/insertBoard")
    public String insetBoard(Board board){
        boardService.insertBoard(board);
        return "redirect:board";
    }

    /*
        상세 글 보기
        @param board
        @param model
        @return
     */
    @RequestMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard((board)));
        return "view";
    }

    @RequestMapping("/updateBoard")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "forward:board";
    }

    @RequestMapping("/deleteBoard")
    public String deleteBoard(Board board){
        boardService.deleteBoard(board);
        return "forward:board";
    }

}