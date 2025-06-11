package com.example.authorbookweb.service;

import com.example.authorbookcommon.entity.Book;
import com.example.authorbookweb.specification.BookSearchCriteria;

import java.util.List;

public interface BookServiceWeb {

    List<Book> findAll();

    void save(Book book);

    Book findById(int id);

    void deleteById(int id);

    void update(Book book);

    List<Book> search(String keyword);

    List<Book> filter(BookSearchCriteria criteria);
}
