package com.codegym.repository;

import com.codegym.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Tự động sinh query: SELECT * FROM comments WHERE date = ?
    List<Comment> findAllByDate(LocalDate date);
}