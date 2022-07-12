package com.example.warproject.controller;

import com.example.warproject.model.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private Member getMember(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return (Member) principal;
        } else {
            return null;
        }
    }

    @RequestMapping("/check")
    public Map<String, Object> check(){
        Map<String, Object> result = new HashMap<>();
        Member member = getMember();
        if(member == null){
            result.put("result", false);
        }
        else{
            result.put("result", true);
            result.put("member", member);
        }
        return result;
    }
}
