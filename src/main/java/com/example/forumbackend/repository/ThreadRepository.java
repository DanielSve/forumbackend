package com.example.forumbackend.repository;

import com.example.forumbackend.model.ForumThread;
import com.example.forumbackend.model.LikeThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<ForumThread, Long> {
    Optional<LikeThread> findByUserId(Long userId);
    List<ForumThread> getForumThreadsByUserId(Long userId);
}
