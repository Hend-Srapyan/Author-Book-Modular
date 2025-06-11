package com.example.authorbookweb.service;

import com.example.authorbookcommon.entity.Author;
import com.example.authorbookweb.specification.AuthorSearchCriteria;
import java.util.List;

public interface AuthorServiceWeb {

    List<Author> findAll();

    void save(Author author);

    void deleteById(int id);

    Author findById(int id);

    void update(Author author);

    List<Author> search(String keyword);

    List<Author> filter(AuthorSearchCriteria searchCriteria);
}
