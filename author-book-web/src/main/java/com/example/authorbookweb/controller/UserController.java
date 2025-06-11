package com.example.authorbookweb.controller;

import com.example.authorbookcommon.entity.User;
import com.example.authorbookweb.service.UserServiceWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceWeb userServiceWeb;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user){
        Optional<User> byEmail = userServiceWeb.findByEmail(user.getEmail());
        if(byEmail.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userServiceWeb.save(user);
        }
        return "redirect:/";
    }
}
