package com.example.authorbookcommon.mapper;

import com.example.authorbookcommon.dto.BookDto;
import com.example.authorbookcommon.dto.SaveBookRequest;
import com.example.authorbookcommon.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    List<BookDto> toDtoList(List<Book> books);

    Book toEntity(SaveBookRequest saveBookRequest);
}