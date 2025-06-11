package com.example.authorbookrest.endpoint;

import com.example.authorbookcommon.dto.AuthorDto;
import com.example.authorbookcommon.dto.SaveAuthorRequest;
import com.example.authorbookrest.service.AuthorService;
import com.example.authorbookrest.security.CurrentUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthorEndpoint {

    private final AuthorService authorService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDto>> getAllAuthors(@AuthenticationPrincipal CurrentUser currentUser) {
        log.info("request from {} user", currentUser.getUsername());
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") int id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping("/authors")
    public ResponseEntity<?> addAuthor(@RequestBody @Valid SaveAuthorRequest authorRequest) {
        if (authorService.findByPhone(authorRequest.getPhone()).isPresent()) {
            return ResponseEntity.badRequest().body("Phone number already exist");
        }
        return ResponseEntity.ok(authorService.save(authorRequest));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") int id) {
        authorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/authors")
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody SaveAuthorRequest authorRequest) {
        return ResponseEntity.ok(authorService.update(authorRequest));
    }
}