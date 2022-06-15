package com.example.warproject.controller;

import com.example.warproject.model.Member;
import com.example.warproject.repositories.MemberRepository;
import com.example.warproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

//    public MemberController(MemberService memberService) {
//
//        this. memberService = memberService;
//    }

    //로그인
    @PostMapping("/login")
    public String signIn(HttpServletResponse response, HttpSession session, String id, String password) throws IOException {
        System.out.println("id : {} , pw : {}"+ id+ password);
        Member member = this.memberService.findMember(id, password);

        if(member != null) {
            String Id = member.getId();
            session.setAttribute("member", Id);
            return "redirect:/";
        }else {
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= response.getWriter();
            out.println("<script>alert('아이디 비밀번호가 다릅니다.'); location.href='/' </script>");
            out.flush();
            return "redirect:/";
        }
    }

    //로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session) throws IOException {
        String logincheck = "0";
        if(!(session.getAttribute("member") == null)){
            logincheck = "1";
        }
        if (logincheck == "1"){
            session.invalidate();
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= response.getWriter();
            out.println("<script>alert('로그아웃 되었습니다..'); location.href='/' </script>");
            out.flush();
        }
        else if(logincheck == "0"){
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= response.getWriter();
            out.println("<script>alert('아직 로그인하지 않았습니다.'); location.href='/' </script>");
            out.flush();
        }
        return "redirect:/";
    }

    //회원가입
    @PostMapping("/signup")
    public String create(Member member) {
        System.out.println(member);
        member.setDate(LocalDate.now());
//        this.memberService.save(member);
        return "redirect:/";
    }
}
