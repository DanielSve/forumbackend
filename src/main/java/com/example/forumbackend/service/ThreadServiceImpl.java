package com.example.forumbackend.service;

import com.example.forumbackend.dto.CommentDto;
import com.example.forumbackend.dto.ForumThreadDto;
import com.example.forumbackend.dto.LikeThreadDto;
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
        ForumThread f = new ForumThread(forumThreadDto.getTitle(),forumThreadDto.getContent(),u);
        return threadRepository.save(f);
    }

    @Override
    public ForumThread addComment(CommentDto commentDto) {
        User u = userRepository.findById(commentDto.getUserId()).orElse(null);
        Comment comment = new Comment(commentDto.getContent(),u);
        commentRepository.save(comment);
        ForumThread f = threadRepository.findById(commentDto.getThreadId()).orElse(null);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        if(f.getComments()!=null) {
            comments.addAll(f.getComments());
        }
        f.setComments(comments);
        return threadRepository.save(f);
    }

    public ForumThread getById(Long id) {
        return threadRepository.findById(id).orElse(null);
    }

    @Override
    public ForumThread toggleLike(LikeThreadDto likeThreadDto) {
        ForumThread f = threadRepository.findById(likeThreadDto.getThreadId()).orElse(null);
        return deleteLikeIfExists(f, likeThreadDto) ?
                null :
                addLikeToThread(f,likeThreadDto);
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
