package com.example.warproject.service;

import com.example.warproject.model.Member;
import com.example.warproject.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Member findMember(String id, String password){
        return memberRepository.findMember(id, password);
    }
}
