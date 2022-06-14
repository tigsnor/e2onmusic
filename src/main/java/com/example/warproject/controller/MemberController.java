package com.example.warproject.controller;

import com.example.warproject.model.Member;
import com.example.warproject.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Slf4j
@Controller
public class MemberController {
    private MemberRepository member;

    public MemberController(MemberRepository member) {
        this.member = member;
    }

//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }

    //로그인
    @PostMapping("/login")
    public String signIn(HttpServletRequest request, Model model, HttpSession session, String id, String password) {
        System.out.println("id : {} , pw : {}"+ id+ password);
        Member member = this.member.findMember(id, password);

        if(member != null) {
            String Id = member.getId();
            session.setAttribute("member", Id);
            return "redirect:/";
        }else {
            session.setAttribute("result", 2);
            return "redirect:/";
        }
    }

    //로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session){
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

    //회원가입
    @PostMapping("/signup")
    public String create(Member member) {
        System.out.println(member);
        member.setDate(LocalDate.now());
        this.member.save(member);
        return "redirect:/";
    }
}
