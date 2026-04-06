package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        User existing = userRepo.findByEmail(user.getEmail());

        if (existing != null && existing.getPassword().equals(user.getPassword())) {
            return existing;
        }

        return null;
    }
}