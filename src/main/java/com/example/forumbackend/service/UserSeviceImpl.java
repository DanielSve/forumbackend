package com.example.forumbackend.service;

import com.example.forumbackend.dto.UserInfoDto;
import com.example.forumbackend.model.Authority;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.AuthoritiesRepository;
import com.example.forumbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class UserSeviceImpl implements UserService{

    UserRepository userRepository;

    AuthoritiesRepository authoritiesRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserSeviceImpl(UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority auth = authoritiesRepository.getByLevelOfAuthority("Auth1").orElse(null);
        user.setAuthorities(Set.of(auth));
        return userRepository.save(user);
    }

    @Override
    public String authorization(String username, String password) {
        Optional<User> u = userRepository.findByUsernameAndPassword(username, password);
        return u.map(user -> user.getId().toString()).orElse("Unauthorized");
    }

    @Override
    public UserInfoDto getUserInfoById(Long userId) {
        User u = userRepository.findById(userId).orElse(null);
        return new UserInfoDto(u.getId(),u.getUsername(),u.getEmail(),null);
    }
}

