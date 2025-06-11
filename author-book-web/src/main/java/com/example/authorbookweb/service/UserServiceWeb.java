package com.example.authorbookweb.service;

import com.example.authorbookcommon.entity.User;
import java.util.Optional;

public interface UserServiceWeb {

    void save(User user);

    Optional<User> findByEmail(String email);
}
