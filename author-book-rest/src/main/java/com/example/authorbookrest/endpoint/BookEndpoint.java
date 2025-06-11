package com.example.authorbookrest.endpoint;

import com.example.authorbookcommon.dto.BookDto;
import com.example.authorbookcommon.dto.SaveBookRequest;
import com.example.authorbookrest.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookEndpoint {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") int id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid SaveBookRequest saveBookRequest) {
        return ResponseEntity.ok(bookService.save(saveBookRequest));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/books")
    public ResponseEntity<BookDto> updateBook(@RequestBody SaveBookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(bookRequest));
    }
}