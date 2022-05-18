package com.example.forumbackend.repository;

import com.example.forumbackend.model.Comment;
import com.example.forumbackend.model.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getByForumThread(ForumThread forumThread);
}
