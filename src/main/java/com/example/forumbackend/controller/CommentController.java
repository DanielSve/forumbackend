package com.example.forumbackend.controller;

import com.example.forumbackend.dto.LikeCommentDto;
import com.example.forumbackend.model.Comment;
import com.example.forumbackend.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/like")
    public Comment likeComment(@RequestBody LikeCommentDto likeCommentDto) {
        System.out.println("COMMENT " + likeCommentDto.getCommentId() + " user " + likeCommentDto.getUserId());
        return commentService.addLike(likeCommentDto);
    }
}
