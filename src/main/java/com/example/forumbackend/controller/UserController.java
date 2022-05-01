package com.example.forumbackend.controller;

import com.example.forumbackend.dto.LoginDto;
import com.example.forumbackend.model.User;
import com.example.forumbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User user(@RequestBody User user) {
        System.out.println(user);
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String authorize(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        return userService.authorization(loginDto.getUsername(), loginDto.getPassword());
    }

    @GetMapping("/all")
    public String getAllUsers() {
        return "allUsers!";
    }
}
