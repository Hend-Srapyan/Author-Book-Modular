package com.example.authorbookweb.service.impl;

import com.example.authorbookcommon.entity.Author;
import com.example.authorbookweb.repository.AuthorRepository;
import com.example.authorbookweb.service.AuthorServiceWeb;
import com.example.authorbookweb.specification.AuthorSearchCriteria;
import com.example.authorbookweb.specification.AuthorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AuthorServiceWeb")
@RequiredArgsConstructor
public class AuthorServiceWebImpl implements AuthorServiceWeb {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> search(String keyword) {
        return authorRepository.findAllByNameContainingOrSurnameContaining(keyword, keyword);
    }

    @Override
    public List<Author> filter(AuthorSearchCriteria searchCriteria) {
        AuthorSpecification authorSpecification = new AuthorSpecification(searchCriteria);
        List<Author> all = authorRepository.findAll(authorSpecification);
        return all;
    }
}
