package com.example.forumbackend.controller;

import com.example.forumbackend.dto.LoginDto;
import com.example.forumbackend.dto.UserInfoDto;
import com.example.forumbackend.model.User;
import com.example.forumbackend.service.LoginService;
import com.example.forumbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    UserService userService;

    LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @PostMapping("/add")
    public User user(@RequestBody User user) {
        System.out.println(user);
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public UserInfoDto authorize(@RequestBody LoginDto loginDto) throws Exception {
        System.out.println(loginDto);
        return loginService.login(loginDto);
    }

    @GetMapping("/getUserInfo/{userId}")
    public UserInfoDto getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfoById(userId);
    }

    @GetMapping("/all")
    public String getAllUsers() {
        return "allUsers!";
    }
}
