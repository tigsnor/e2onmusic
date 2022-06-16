package com.example.warproject.service;

import com.example.warproject.model.Member;
import com.example.warproject.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/*
    @NonNull 어노테이션이 붙은 필드와 final로 선언된 필드들에 대해 생성자를 만들어낸다.
    이를 통해 Constructor Injection이 가능하다.
*/
@Service
public class MemberService implements UserDetailsService { //UserDetailService 시큐리티에서 제공하는 인터페이스

    @Autowired
    private PasswordEncoder passwordEncoder;
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
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findAccountByUsername(username).orElseThrow(() -> new UsernameNotFoundException((username)));
//        return User.builder()
//                .username(member.getUsername())
//                .password(member.getPassword())
//                .roles(member.getRole())
//                .build();
    }

    public Integer save(Member member){
        member.encodePassword(passwordEncoder);//여기서 패스워드 인코딩

        return memberRepository.save(Member.builder()
            .username(member.getUsername())
            .role(member.getRole())
            .password(member.getPassword()).build()).getId();
    }

//    @Transactional
//    public Member findMember(String id, String password){
//        return memberRepository.findMember(id, password);
//    }
}
