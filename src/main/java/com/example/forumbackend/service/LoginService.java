package com.example.forumbackend.service;


import com.example.forumbackend.dto.LoginDto;
import com.example.forumbackend.dto.UserInfoDto;
import com.example.forumbackend.model.User;
import com.example.forumbackend.repository.UserRepository;
import com.example.forumbackend.security.JwtUtil;
import com.example.forumbackend.security.SecurityUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User was not found in Database."));
        return new SecurityUser(user);
    }

    public UserInfoDto login(LoginDto loginDto) throws Exception {
        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User was not found in Database."));
        if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            String token = jwtUtil.generateToken(new SecurityUser(user));

            return new UserInfoDto(user.getId(),user.getUsername(),user.getEmail(),token);
        }
        throw new Exception("This is exception in LoginService!");
    }
}

