package com.codegym.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score; // Thang điểm từ 1 đến 5
    private String author; // Tên tác giả
    private String feedback; // Lời nhận xét

    private Integer likes = 0; // Số lượt like (mặc định = 0)

    private LocalDate date; // Ngày bình luận (để lọc theo ngày hiện tại)

    public Comment() {
        this.date = LocalDate.now();
        this.likes = 0;
    }

    public Comment(Integer score, String author, String feedback) {
        this.score = score;
        this.author = author;
        this.feedback = feedback;
        this.likes = 0;
        this.date = LocalDate.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Integer getLikes() { return likes; }
    public void setLikes(Integer likes) { this.likes = likes; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}