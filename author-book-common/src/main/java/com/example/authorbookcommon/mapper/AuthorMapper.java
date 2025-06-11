package com.example.authorbookcommon.mapper;

import com.example.authorbookcommon.dto.AuthorDto;
import com.example.authorbookcommon.dto.SaveAuthorRequest;
import com.example.authorbookcommon.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    List<AuthorDto> toDtoList(List<Author> authors);

    Author toEntity(SaveAuthorRequest authorRequest);
}