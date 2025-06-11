package com.example.authorbookweb.security;

import com.example.authorbookcommon.entity.User;
import com.example.authorbookweb.service.UserServiceWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserServiceWeb userServiceWeb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userServiceWeb.findByEmail(username);
        if (byEmail.isPresent()) {
           User userFromDB = byEmail.get();
            return new CurrentUser(userFromDB);
        }
            throw new UsernameNotFoundException("User with" + username + "does not exist");
    }
}
