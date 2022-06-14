package com.example.warproject.repositories;

import com.example.warproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "select m from Member m where id = :id and password = :password")
    Member findMember(String id, String password);
//    Optional<Member> findByUserId(String userId);
}
