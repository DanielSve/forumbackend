package com.example.forumbackend.service;

import com.example.forumbackend.dto.LikeCommentDto;
import com.example.forumbackend.model.Comment;

public interface CommentService {
    Comment toggleLikeComment(LikeCommentDto LikeCommentDto);
}
