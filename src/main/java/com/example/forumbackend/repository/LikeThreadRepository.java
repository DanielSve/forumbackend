package com.example.forumbackend.repository;

import com.example.forumbackend.model.LikeThread;
import com.example.forumbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeThreadRepository extends JpaRepository<LikeThread, Long> {
    Optional<LikeThread> findByUserId(Long userId);
}
