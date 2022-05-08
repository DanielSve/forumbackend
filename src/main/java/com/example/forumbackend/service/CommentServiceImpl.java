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
import java.util.Arrays;
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
    public Comment toggleLikeComment(LikeCommentDto likeCommentDto) {
        User u = userRepository.findById(likeCommentDto.getUserId()).orElse(null);
        Comment c = commentRepository.findById(likeCommentDto.getCommentId()).orElse(null);
        return deleteLikeIfExists(c, likeCommentDto) ?
                null :
                addLikeToComment(c,u);
    }

    public Comment addLikeToComment(Comment c, User user) {
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

    public boolean deleteLikeIfExists(Comment c, LikeCommentDto likeCommentDto) {
        for ( LikeComment likeComment : c.getLikeComments()){
            if(likeComment.getUser().getId() == likeCommentDto.getUserId()) {
                likeCommentRepository.deleteById(likeComment.getId());
                return true;
            }
        }
        return false;
    }
}
