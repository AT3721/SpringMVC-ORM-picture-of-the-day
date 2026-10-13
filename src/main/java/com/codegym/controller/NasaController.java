package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/nasa")
public class NasaController {

    @Autowired
    private CommentRepository commentRepository;

    // Hiển thị trang bức ảnh và danh sách bình luận của NGÀY HIỆN TẠI
    @GetMapping
    public String showPage(Model model) {
        // Chỉ lấy những comment của ngày hôm nay
        List<Comment> todayComments = commentRepository.findAllByDate(LocalDate.now());

        model.addAttribute("commentForm", new Comment());
        model.addAttribute("comments", todayComments);
        return "index";
    }

    // Gửi bình luận mới
    @PostMapping("/comment")
    public String addComment(@ModelAttribute("commentForm") Comment comment) {
        comment.setDate(LocalDate.now()); // Đảm bảo ngày là ngày hiện tại
        comment.setLikes(0);
        commentRepository.save(comment); // Lưu vào MySQL thông qua Hibernate
        return "redirect:/nasa";
    }

    // Nút Like bình luận
    @PostMapping("/like/{id}")
    public String likeComment(@PathVariable("id") Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setLikes(comment.getLikes() + 1);
            commentRepository.save(comment); // Cập nhật số like vào CSDL
        }
        return "redirect:/nasa";
    }
}