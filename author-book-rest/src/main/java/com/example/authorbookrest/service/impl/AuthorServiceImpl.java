package com.example.authorbookrest.service.impl;

import com.example.authorbookcommon.dto.AuthorDto;
import com.example.authorbookcommon.dto.SaveAuthorRequest;
import com.example.authorbookcommon.entity.Author;
import com.example.authorbookcommon.exception.AuthorNotFoundException;
import com.example.authorbookcommon.mapper.AuthorMapper;
import com.example.authorbookrest.repository.AuthorRepository;
import com.example.authorbookrest.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toDtoList(authors);
    }

    @Override
    public AuthorDto save(SaveAuthorRequest authorRequest) {
        Author author = authorRepository.save(authorMapper.toEntity(authorRequest));
        return authorMapper.toDto(author);
    }

    @Override
    public void deleteById(int id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Author not found with " + id + " id");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDto findById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException("Author not found with " + id + " id"));
        if (author == null) {
            return null;
        }
        return authorMapper.toDto(author);
    }

    @Override
    public AuthorDto update(SaveAuthorRequest authorRequest) {
        Author author = authorRepository.save(authorMapper.toEntity(authorRequest));
        return authorMapper.toDto(author);
    }

    @Override
    public Optional<Author> findByPhone(String phone) {
        return authorRepository.findByPhone(phone);
    }
}