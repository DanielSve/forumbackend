package com.example.forumbackend.controller;

import com.example.forumbackend.dto.*;
import com.example.forumbackend.model.Comment;
import com.example.forumbackend.model.ForumThread;
import com.example.forumbackend.service.ThreadService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/threads")
@CrossOrigin
public class ThreadController {

    ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @PostMapping("/add")
    public ForumThread postThread(@RequestBody ForumThreadDto forumThreadDto) {
        return threadService.addThread(forumThreadDto);
    }

    @PostMapping("/addComment")
    public Comment addCommentToThread(@RequestBody CommentDto commentDto) {
        return threadService.addComment(commentDto);
    }

    @GetMapping("/getById/{id}")
    public ThreadWithCommentsDto getById(@PathVariable Long id) {
        System.out.println(threadService.getById(id));
        return threadService.getById(id);
    }

    @GetMapping("/getByUserId/{userId}")
    public List<ForumThread> getByUserId(@PathVariable Long userId) {
      return threadService.getThreadsByUserId(userId);
    }

    @GetMapping("/all")
    public List<ForumThread> getAll() {
        return threadService.getAllThreads();
    }

    @PostMapping("/like")
    public ForumThread toggleLikeThread(@RequestBody LikeThreadDto likeThreadDto) {
        return threadService.toggleLike(likeThreadDto);
    }
}
