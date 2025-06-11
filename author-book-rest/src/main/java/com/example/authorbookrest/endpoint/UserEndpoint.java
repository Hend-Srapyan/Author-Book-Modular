package com.example.authorbookrest.endpoint;

import com.example.authorbookcommon.dto.SaveUserRequest;
import com.example.authorbookcommon.dto.UserAuthRequest;
import com.example.authorbookcommon.dto.UserAuthResponse;
import com.example.authorbookcommon.entity.User;
import com.example.authorbookcommon.mapper.UserMapper;
import com.example.authorbookrest.service.UserService;
import com.example.authorbookrest.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Email or password does not exist"),
            @ApiResponse(responseCode = "200",description = "Login is success")
    })
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserAuthRequest userAuthRequest) {

        Optional<User> byEmail = userService.findByEmail(userAuthRequest.getEmail());

        if (byEmail.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        User user = byEmail.get();
        if (passwordEncoder.matches(userAuthRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(UserAuthResponse.builder()
                    .token(jwtTokenUtil.generateToken(user.getEmail()))
                            .name(user.getName())
                            .surname(user.getSurname())
                            .userId(user.getId())
                    .build());
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Email already exists"),
            @ApiResponse(responseCode = "200",description = "Registration is success")
    })
    public ResponseEntity<?> register(@RequestBody SaveUserRequest saveUserRequest) {
        if (userService.findByEmail(saveUserRequest.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        saveUserRequest.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
        userService.save(userMapper.toEntity(saveUserRequest));
        return ResponseEntity
                .ok()
                .build();
    }
}