package com.example.authorbookweb.service.impl;

import com.example.authorbookcommon.entity.Book;
import com.example.authorbookweb.repository.BookRepository;
import com.example.authorbookweb.service.BookServiceWeb;
import com.example.authorbookweb.specification.BookSearchCriteria;
import com.example.authorbookweb.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service("BookServiceWeb")
@RequiredArgsConstructor
public class BookServiceWebImpl implements BookServiceWeb {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        book.setCreatedAt(new Date());
        bookRepository.save(book);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(Book book) {
        book.setCreatedAt(new Date());
        bookRepository.save(book);
    }

    @Override
    public List<Book> search(String keyword) {
        return bookRepository.findAllByTitleContaining(keyword);
    }

    @Override
    public List<Book> filter(BookSearchCriteria criteria) {
        BookSpecification bookSpecification = new BookSpecification(criteria);
        List<Book> all = bookRepository.findAll(bookSpecification);
        return all;
    }
}
