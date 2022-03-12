package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findByContentContaining(String content);

    List<Board> findByTitleContaining(String title);

    @Modifying
    @Query("update Board p set p.count = p.count + 1 where p.id = :id")
    int updateView(int id);
}
