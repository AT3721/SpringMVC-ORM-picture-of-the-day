package com.codegym.repository;

import com.codegym.model.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional // Tự động quản lý Transaction của JPA
public class CommentRepository implements ICommentRepository {

    // Tiêm EntityManager chuẩn JPA vào
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findAllToday() {
        // Sử dụng câu lệnh JPQL (Java Persistence Query Language)
        String jpql = "SELECT c FROM Comment c WHERE c.date = :today";
        TypedQuery<Comment> query = entityManager.createQuery(jpql, Comment.class);
        query.setParameter("today", LocalDate.now());
        return query.getResultList();
    }

    @Override
    public Comment findById(Long id) {
        // Dùng phương thức find() chuẩn của JPA EntityManager
        return entityManager.find(Comment.class, id);
    }

    @Override
    public void save(Comment comment) {
        if (comment.getId() == null) {
            // Thêm mới entity vào Persistence Context (Insert)
            entityManager.persist(comment);
        } else {
            // Cập nhật entity vào Persistence Context (Update)
            entityManager.merge(comment);
        }
    }
}