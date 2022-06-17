package com.example.warproject.controller;

import com.example.warproject.model.Member;
import com.example.warproject.repositories.MemberRepository;
import com.example.warproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원가입
    //url로 받은 정보들을 Member 객체로 맵핑하여 MemberService에서 저장을 시켜준다.
    @PostMapping("/user")
    public String signup(Member member){
        memberService.save(member);
        return "redirect:/board";
    }

    //로그아웃
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/board";
    }
}
