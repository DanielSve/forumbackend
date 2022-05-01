package com.example.forumbackend.repository;

import com.example.forumbackend.model.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<ForumThread, Long> {
}
