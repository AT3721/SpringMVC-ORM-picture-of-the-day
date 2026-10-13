package com.codegym.repository;

import com.codegym.model.Comment;
import java.util.List;

public interface ICommentRepository {
    List<Comment> findAllToday();
    Comment findById(Long id);
    void save(Comment comment);
}