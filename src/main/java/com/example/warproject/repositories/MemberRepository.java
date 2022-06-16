package com.example.warproject.repositories;

import com.example.warproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findAccountByUsername(String username);//username으로 조회 //Optional??
//    Optional<Member> findByUserId(String userId);
}
