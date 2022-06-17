package com.example.warproject.repositories;

import com.example.warproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findAccountByUsername(String username);//username으로 조회
    // Optional은 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피가능
}
