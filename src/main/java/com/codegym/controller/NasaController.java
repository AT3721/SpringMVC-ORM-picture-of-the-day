package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/nasa")
public class NasaController {

    @Autowired
    private ICommentRepository commentRepository;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("commentForm", new Comment());
        model.addAttribute("comments", commentRepository.findAllToday());
        return "index";
    }

    @PostMapping("/comment")
    public String addComment(@ModelAttribute("commentForm") Comment comment) {
        comment.setDate(LocalDate.now());
        comment.setLikes(0);
        commentRepository.save(comment); // Gọi JPA persist
        return "redirect:/nasa";
    }

    @PostMapping("/like/{id}")
    public String likeComment(@PathVariable("id") Long id) {
        Comment comment = commentRepository.findById(id); // Gọi JPA find
        if (comment != null) {
            comment.setLikes(comment.getLikes() + 1);
            commentRepository.save(comment); // Gọi JPA merge
        }
        return "redirect:/nasa";
    }
}