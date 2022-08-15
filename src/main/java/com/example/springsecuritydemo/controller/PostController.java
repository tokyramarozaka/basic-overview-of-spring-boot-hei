package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long userId){
        return userService.getById(userId);
    }

    @PostMapping("/")
    public List<User> saveUsers(List<User> users){
        return userService.saveAll(users);
    }
}
