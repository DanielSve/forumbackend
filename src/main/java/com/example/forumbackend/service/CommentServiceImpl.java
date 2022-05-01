package com.example.forumbackend.service;

import com.example.forumbackend.dto.LikeCommentDto;
import com.example.forumbackend.model.Comment;
import com.example.forumbackend.model.LikeComment;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.CommentRepository;
import com.example.forumbackend.repository.LikeCommentRepository;
import com.example.forumbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService{

    CommentRepository commentRepository;
    UserRepository userRepository;
    LikeCommentRepository likeCommentRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, LikeCommentRepository likeCommentRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.likeCommentRepository = likeCommentRepository;
    }

    @Override
    public Comment addLike(LikeCommentDto likeCommentDto) {
        User user = userRepository.findById(likeCommentDto.getUserId()).orElse(null);
        Comment c = commentRepository.findById(likeCommentDto.getCommentId()).orElse(null);
        if(c.getLikeComments().stream().anyMatch(l -> Objects.equals(l.getUser().getId(), likeCommentDto.getUserId()))) {
            Long id = c.getLikeComments().stream().filter(l ->
                    l.getUser().getId().equals(likeCommentDto.getUserId())).toList().get(0).getId();
            likeCommentRepository.deleteById(id);
            return null;
        } else {
            LikeComment likeComment = new LikeComment(user);
            likeCommentRepository.save(likeComment);
            List<LikeComment> likeComments = new ArrayList<>();
            likeComments.add(likeComment);
            if (c.getLikeComments() != null) {
                c.addLikeComment(likeComment);
            } else {
                c.setLikeComments(likeComments);
            }
            return commentRepository.save(c);
        }
    }
}
