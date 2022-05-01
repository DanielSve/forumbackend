package com.example.forumbackend.service;

import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserSeviceImpl implements UserService{


    UserRepository userRepository;

    public UserSeviceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String authorization(String username, String password) {
        Optional<User> u = userRepository.findByUsernameAndPassword(username, password);
        return u.map(user -> user.getId().toString()).orElse("Unauthorized");
    }
}
