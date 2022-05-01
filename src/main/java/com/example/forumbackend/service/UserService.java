package com.example.forumbackend.service;

import com.example.forumbackend.model.User;

import java.util.Optional;

public interface UserService {

    User createUser(User user);
    String authorization(String username, String password);
}
