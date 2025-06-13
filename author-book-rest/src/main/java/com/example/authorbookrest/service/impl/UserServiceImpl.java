package com.example.authorbookrest.service.impl;

import com.example.authorbookcommon.entity.User;
import com.example.authorbookrest.repository.UserRepository;
import com.example.authorbookrest.service.MailService;
import com.example.authorbookrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public User save(User user) {
        User savedUser = userRepository.save(user);
        mailService.sendMail(savedUser.getEmail(), "Welcome Author Book Application.", "You have successfully registered the author book application.");

        return savedUser;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}