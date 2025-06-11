package com.example.authorbookrest.service.impl;

import com.example.authorbookcommon.dto.BookDto;
import com.example.authorbookcommon.dto.SaveBookRequest;
import com.example.authorbookcommon.entity.Book;
import com.example.authorbookcommon.exception.BookNotFoundException;
import com.example.authorbookcommon.mapper.BookMapper;
import com.example.authorbookrest.repository.BookRepository;
import com.example.authorbookrest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDtoList(books);
    }

    @Override
    public BookDto save(SaveBookRequest bookRequest) {
        Book book = bookRepository.save(bookMapper.toEntity(bookRequest));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto findById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with " + id + " id"));
        if (book == null) {
            return null;
        }
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(int id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with " + id + " id");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(SaveBookRequest bookRequest) {

        Book book = bookRepository.save(bookMapper.toEntity(bookRequest));
        return bookMapper.toDto(book);
    }
}