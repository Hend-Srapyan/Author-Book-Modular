package com.example.authorbookrest.service;

import com.example.authorbookcommon.dto.AuthorDto;
import com.example.authorbookcommon.dto.SaveAuthorRequest;
import com.example.authorbookcommon.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<AuthorDto> findAll();

    AuthorDto save(SaveAuthorRequest authorRequest);

    void deleteById(int id);

    AuthorDto findById(int id);

    AuthorDto update(SaveAuthorRequest authorRequest);

    Optional<Author> findByPhone(String phone);
}