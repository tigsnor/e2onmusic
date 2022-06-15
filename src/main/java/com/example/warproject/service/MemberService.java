package com.example.warproject.service;

import com.example.warproject.model.Member;
import com.example.warproject.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//UserDetailService 시큐리티에서 제공하는 인터페이스
@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    /*
    loadUserByUsername메소드를 사용하면 데이터베이스에 있는 유저 정보를 로그인에 사용하는 메소드
    재정의를 통해 로그인 유저를 가져오는 방법을 커스텀해줄 수 있다.

    이 메소드는 username을 통해 계정정보를 가져와서
    UserDetails 라는 타입으로 리턴해주기 위한 메소드이다.
    UserDetails에 name, password, role를 주입시켜 생성한다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findAccountByUsername(username);
        if(member == null){
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }

    public Member createNew(Member member){
        member.encodePassword(member);
        return this.memberRepository.save(member);
    }

//    @Transactional
//    public Member findMember(String id, String password){
//        return memberRepository.findMember(id, password);
//    }
}
