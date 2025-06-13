package com.example.authorbookrest.service.impl;

import com.example.authorbookcommon.entity.User;
import com.example.authorbookrest.repository.UserRepository;
import com.example.authorbookrest.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailService mailService;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setEmail("test@example.com");

    }

    @Test
    void save_shouldSaveUserAndSendMail() {
        when(userRepository.save(user)).thenReturn(user);
        doNothing().when(mailService).sendMail(anyString(), anyString(), anyString());

        User result = userService.save(user);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());

        verify(mailService, times(1)).sendMail(eq("test@example.com"),
                eq("Welcome Author Book Application."),
                eq("You have successfully registered the author book application."));
    }

    @Test
    void findByEmail_shouldReturnUserIfPresent() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }

    @Test
    void findByEmail_shouldReturnEmptyIfNotPresent() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail("notfound@example.com");

        assertFalse(result.isPresent());
    }
}