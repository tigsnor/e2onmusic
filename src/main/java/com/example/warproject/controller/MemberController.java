package com.example.warproject.controller;

import com.example.warproject.model.Member;
import com.example.warproject.repositories.MemberRepository;
import com.example.warproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/member/{role}/username}/{password}")
    public Member createAccount(@ModelAttribute Member member){
        Member newMember = memberService.createNew(member);
        return newMember;
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
