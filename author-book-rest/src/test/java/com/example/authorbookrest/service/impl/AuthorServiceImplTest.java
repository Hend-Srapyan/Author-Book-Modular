package com.example.authorbookrest.service.impl;

import com.example.authorbookcommon.dto.AuthorDto;
import com.example.authorbookcommon.dto.SaveAuthorRequest;
import com.example.authorbookcommon.entity.Author;
import com.example.authorbookcommon.exception.AuthorNotFoundException;
import com.example.authorbookcommon.mapper.AuthorMapper;
import com.example.authorbookrest.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorDto authorDto;
    private SaveAuthorRequest saveAuthorRequest;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1);
        author.setName("Author Name");

        authorDto = new AuthorDto();
        authorDto.setId(1);
        authorDto.setName("Author Name");

        saveAuthorRequest = new SaveAuthorRequest();
        saveAuthorRequest.setName("Author Name");

    }

    @Test
    void findAll_shouldReturnListOfAuthorDto() {
        List<Author> authors = List.of(author);
        List<AuthorDto> dtos = List.of(authorDto);

        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.toDtoList(authors)).thenReturn(dtos);

        List<AuthorDto> result = authorService.findAll();

        assertEquals(1, result.size());
        assertEquals("Author Name", result.get(0).getName());
    }

    @Test
    void save_shouldSaveAuthorAndReturnDto() {
        when(authorMapper.toEntity(saveAuthorRequest)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(authorDto);

        AuthorDto result = authorService.save(saveAuthorRequest);

        assertNotNull(result);
        assertEquals("Author Name", result.getName());
    }

    @Test
    void findById_shouldReturnAuthorDtoWhenAuthorExists() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(authorMapper.toDto(author)).thenReturn(authorDto);

        AuthorDto result = authorService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void findById_shouldThrowAuthorNotFoundExceptionWhenNotExists() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.findById(1));
    }

    @Test
    void deleteById_shouldRemoveAuthorWhenExists() {
        when(authorRepository.existsById(1)).thenReturn(true);
        doNothing().when(authorRepository).deleteById(1);

        assertDoesNotThrow(() -> authorService.deleteById(1));
    }

    @Test
    void deleteById_shouldThrowAuthorNotFoundExceptionWhenNotExists() {
        when(authorRepository.existsById(1)).thenReturn(false);

        assertThrows(AuthorNotFoundException.class, () -> authorService.deleteById(1));
    }

    @Test
    void update_shouldSaveAuthorAndReturnDto() {
        when(authorMapper.toEntity(saveAuthorRequest)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(authorDto);

        AuthorDto result = authorService.update(saveAuthorRequest);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Author Name", result.getName());
    }


    @Test
    void findByPhone_shouldReturnAuthorWhenExists() {
        when(authorRepository.findByPhone("12345")).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.findByPhone("12345");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }
}

