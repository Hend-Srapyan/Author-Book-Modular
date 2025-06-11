package com.example.authorbookrest.endpoint;

import com.example.authorbookcommon.dto.BookDto;
import com.example.authorbookcommon.dto.CurrencyResponse;
import com.example.authorbookcommon.dto.SaveBookRequest;
import com.example.authorbookcommon.mapper.BookMapper;
import com.example.authorbookrest.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookEndpoint {

    private final BookService bookService;
    private final RestTemplate restTemplate;

    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        List<BookDto> dtoList = bookService.findAll();
        ResponseEntity<HashMap> forEntity = restTemplate.getForEntity("https://cb.am/latest.json.php", HashMap.class);
        if (forEntity.getStatusCode() == HttpStatus.OK) {
            HashMap<String, String> currencyResponseMap = forEntity.getBody();
            if (currencyResponseMap != null && currencyResponseMap.containsKey("RUB") && currencyResponseMap.get("USD") != null) {
                for (BookDto bookDto : dtoList) {
                    bookDto.setPriceRUB(bookDto.getPrice() / Double.parseDouble(currencyResponseMap.get("RUB")));
                    bookDto.setPriceUSD(bookDto.getPrice() / Double.parseDouble(currencyResponseMap.get("USD")));

                }
            }
        }
        return dtoList;
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