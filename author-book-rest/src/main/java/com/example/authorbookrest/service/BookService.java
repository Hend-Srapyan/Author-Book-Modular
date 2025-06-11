package com.example.authorbookrest.service;

import com.example.authorbookcommon.dto.BookDto;
import com.example.authorbookcommon.dto.SaveBookRequest;
import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    BookDto save(SaveBookRequest bookRequest);

    BookDto findById(int id);

    void deleteById(int id);

    BookDto update(SaveBookRequest bookRequest);

}