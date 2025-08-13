package com.example.SpringBootIntern.Controller;

import com.example.SpringBootIntern.Model.User;
import com.example.SpringBootIntern.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBootIntern.Repository.UserRepository;


@RestController
public class UserController {

     @Autowired
     private UserService userService;

     @Autowired
     private UserRepository userRepository;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "This is a user endpoint";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is an admin endpoint";
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is taken");
        }
         userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }
}

