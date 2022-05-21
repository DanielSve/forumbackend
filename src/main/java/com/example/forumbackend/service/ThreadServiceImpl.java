package com.example.forumbackend.service;

import com.example.forumbackend.dto.CommentDto;
import com.example.forumbackend.dto.ForumThreadDto;
import com.example.forumbackend.dto.LikeThreadDto;
import com.example.forumbackend.dto.ThreadWithCommentsDto;
import com.example.forumbackend.model.Comment;
import com.example.forumbackend.model.ForumThread;
import com.example.forumbackend.model.LikeThread;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.CommentRepository;
import com.example.forumbackend.repository.LikeThreadRepository;
import com.example.forumbackend.repository.ThreadRepository;
import com.example.forumbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ThreadServiceImpl implements ThreadService{

    ThreadRepository threadRepository;
    UserRepository userRepository;
    CommentRepository commentRepository;
    LikeThreadRepository likeThreadRepository;

    public ThreadServiceImpl(ThreadRepository threadRepository, UserRepository userRepository, CommentRepository commentRepository, LikeThreadRepository likeThreadRepository) {
        this.threadRepository = threadRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.likeThreadRepository = likeThreadRepository;
    }

    @Override
    public ForumThread addThread(ForumThreadDto forumThreadDto) {
        User u = userRepository.findById(forumThreadDto.getUserId()).orElse(null);
        ForumThread f = new ForumThread(forumThreadDto.getTitle(),forumThreadDto.getContent(),u, forumThreadDto.getDate());
        System.out.println(f);
        return threadRepository.save(f);
    }

    @Override
    public Comment addComment(CommentDto commentDto) {
        User u = userRepository.findById(commentDto.getUserId()).orElse(null);
        ForumThread f = threadRepository.findById(commentDto.getThreadId()).orElse(null);
        Comment comment = new Comment(commentDto.getContent(),u,f, commentDto.getDate());
        return commentRepository.save(comment);
    }

    public ThreadWithCommentsDto getById(Long id) {
        ForumThread f = threadRepository.findById(id).orElse(null);
        List<Comment> comments = commentRepository.getByForumThread(f);
        return new ThreadWithCommentsDto(f.getId(), f.getTitle(),f.getContent(), f.getUser(), f.getLikeThreads(), comments, f.getDate());
    }

    @Override
    public ForumThread toggleLike(LikeThreadDto likeThreadDto) {
        ForumThread f = threadRepository.findById(likeThreadDto.getThreadId()).orElse(null);
        return deleteLikeIfExists(f, likeThreadDto) ?
                null :
                addLikeToThread(f,likeThreadDto);
    }

    @Override
    public List<ForumThread> getThreadsByUserId(Long userId) {
        return threadRepository.getForumThreadsByUserId(userId);
    }

    public ForumThread addLikeToThread(ForumThread f, LikeThreadDto likeThreadDto) {
        User user = userRepository.findById(likeThreadDto.getUserId()).orElse(null);
        LikeThread likeThread = new LikeThread(user);
        likeThreadRepository.save(likeThread);
        List<LikeThread> likes = new ArrayList<>();
        likes.add(likeThread);
        if (f.getLikeThreads() != null) {
            likes.addAll(f.getLikeThreads());
        }
        f.setLikeThreads(likes);
        return threadRepository.save(f);
    }

    public boolean deleteLikeIfExists (ForumThread f, LikeThreadDto likeThreadDto) {
        for (int i = 0; i < f.getLikeThreads().size() ; i++) {
            if(f.getLikeThreads().get(i).getUser().getId()== likeThreadDto.getUserId()) {
                likeThreadRepository.deleteById(f.getLikeThreads().get(i).getId());
                return true;
            }
        }
        return false;
    }

    public List<ForumThread> getAllThreads() {
        return threadRepository.findAll();
    }
}
