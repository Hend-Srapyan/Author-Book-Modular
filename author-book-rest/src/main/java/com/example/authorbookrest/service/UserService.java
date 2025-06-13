package com.example.authorbookrest.service;

import com.example.authorbookcommon.entity.User;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByEmail(String email);
}