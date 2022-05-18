package com.example.forumbackend.repository;

import com.example.forumbackend.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> getByLevelOfAuthority(String levelOfAuthority);
}
