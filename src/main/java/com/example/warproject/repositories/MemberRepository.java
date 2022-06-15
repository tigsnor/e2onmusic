package com.example.warproject.repositories;

import com.example.warproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findAccountByUsername(String username);
//    Optional<Member> findByUserId(String userId);
}
