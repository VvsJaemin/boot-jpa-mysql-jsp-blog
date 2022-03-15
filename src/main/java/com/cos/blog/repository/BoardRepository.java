package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Page<Board> findByContentContainingIgnoreCase(String content, Pageable pageable, String field);

    Page<Board> findByTitleContainingIgnoreCase(String title, Pageable pageable, String field);

    @Modifying
    @Query("update Board p set p.count = p.count + 1 where p.id = :id")
    int updateView(int id);
}
