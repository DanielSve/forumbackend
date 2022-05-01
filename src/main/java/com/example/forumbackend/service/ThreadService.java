package com.example.forumbackend.service;

import com.example.forumbackend.dto.CommentDto;
import com.example.forumbackend.dto.ForumThreadDto;
import com.example.forumbackend.model.ForumThread;

import java.util.List;

public interface ThreadService {

    ForumThread addThread(ForumThreadDto forumThreadDto);
    ForumThread addComment(CommentDto commentDto);
    List<ForumThread> getAllThreads();
    ForumThread getById(Long id);
}
