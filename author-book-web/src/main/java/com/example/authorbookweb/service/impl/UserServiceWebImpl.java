package com.example.authorbookweb.service.impl;

import com.example.authorbookcommon.entity.User;
import com.example.authorbookweb.repository.UserRepository;
import com.example.authorbookweb.service.UserServiceWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserServiceWeb")
@RequiredArgsConstructor
public class UserServiceWebImpl implements UserServiceWeb {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
