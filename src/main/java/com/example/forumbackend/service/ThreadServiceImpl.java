package com.example.forumbackend.service;

import com.example.forumbackend.dto.CommentDto;
import com.example.forumbackend.dto.ForumThreadDto;
import com.example.forumbackend.model.Comment;
import com.example.forumbackend.model.ForumThread;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.CommentRepository;
import com.example.forumbackend.repository.ThreadRepository;
import com.example.forumbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ThreadServiceImpl implements ThreadService{

    ThreadRepository threadRepository;
    UserRepository userRepository;
    CommentRepository commentRepository;

    public ThreadServiceImpl(ThreadRepository threadRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.threadRepository = threadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public ForumThread addThread(ForumThreadDto forumThreadDto) {
        User u = userRepository.findById(forumThreadDto.getUserId()).orElse(null);
        ForumThread f = new ForumThread(forumThreadDto.getTitle(),forumThreadDto.getContent(),u);
//        f.setTitle(forumThreadDto.getTitle());
//        f.setContent(forumThreadDto.getContent());
//        f.setUser(u);
        return threadRepository.save(f);
    }

    @Override
    public ForumThread addComment(CommentDto commentDto) {
        User u = userRepository.findById(commentDto.getUserId()).orElse(null);
        Comment comment = new Comment(commentDto.getContent(),u);
//        comment.setUser(u);
//        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);

        ForumThread f = threadRepository.findById(commentDto.getThreadId()).orElse(null);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        if(f.getComments()!=null) {
            comments.addAll(f.getComments());
        }
        System.out.println(f);
        f.setComments(comments);
        return threadRepository.save(f);
    }

    public ForumThread getById(Long id) {
        return threadRepository.findById(id).orElse(null);
    }

    public List<ForumThread> getAllThreads() {
        return threadRepository.findAll();
    }
}
