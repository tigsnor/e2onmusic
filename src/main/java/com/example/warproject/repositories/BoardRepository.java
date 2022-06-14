package com.example.warproject.repositories;

import com.example.warproject.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Board 엔티티를 이용한 데이터베이스에 조회/생성/수정/삭제 담당
@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
    Page<Board> findByTitleContaining(String keyword, Pageable pageable);

    Page<Board> findAll(Pageable pageable);
}
