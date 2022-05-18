package com.example.forumbackend.service;

import com.example.forumbackend.dto.CommentDto;
import com.example.forumbackend.dto.ForumThreadDto;
import com.example.forumbackend.dto.LikeThreadDto;
import com.example.forumbackend.dto.ThreadWithCommentsDto;
import com.example.forumbackend.model.Comment;
import com.example.forumbackend.model.ForumThread;

import java.util.List;

public interface ThreadService {

    ForumThread addThread(ForumThreadDto forumThreadDto);
    Comment addComment(CommentDto commentDto);
    List<ForumThread> getAllThreads();
    ThreadWithCommentsDto getById(Long id);
    ForumThread toggleLike(LikeThreadDto likeThreadDto);
    List<ForumThread> getThreadsByUserId(Long userId);
}
