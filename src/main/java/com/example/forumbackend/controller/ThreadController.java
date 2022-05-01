package com.example.forumbackend.controller;

import com.example.forumbackend.dto.CommentDto;
import com.example.forumbackend.dto.ForumThreadDto;
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
    public ForumThread addCommentToThread(@RequestBody CommentDto commentDto) {
        return threadService.addComment(commentDto);
    }

    @GetMapping("/getById/{id}")
    public ForumThread getById(@PathVariable Long id) {
        return threadService.getById(id);
    }

    @GetMapping("/all")
    public List<ForumThread> getAll() {
        return threadService.getAllThreads();
    }
}
