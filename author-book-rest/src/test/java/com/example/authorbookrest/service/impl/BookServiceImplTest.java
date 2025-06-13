package com.example.authorbookrest.service.impl;

import com.example.authorbookcommon.dto.BookDto;
import com.example.authorbookcommon.dto.SaveBookRequest;
import com.example.authorbookcommon.entity.Book;
import com.example.authorbookcommon.exception.BookNotFoundException;
import com.example.authorbookcommon.mapper.BookMapper;
import com.example.authorbookrest.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookDto bookDto;
    private SaveBookRequest bookRequest;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1);
        book.setTitle("Book Title");

        bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setTitle("Book Title");

        bookRequest = new SaveBookRequest();
        bookRequest.setTitle("Book Title");

    }

    @Test
    void findAll_shouldReturnListOfBookDto() {
        List<Book> books = List.of(book);
        List<BookDto> dtos = List.of(bookDto);

        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toDtoList(books)).thenReturn(dtos);

        List<BookDto> result = bookService.findAll();

        assertEquals(1, result.size());
        assertEquals("Book Title", result.get(0).getTitle());
    }

    @Test
    void save_shouldSaveBookAndReturnDto() {
        when(bookMapper.toEntity(bookRequest)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto result = bookService.save(bookRequest);

        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
    }

    @Test
    void findById_shouldReturnBookDtoIfBookExists() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto result = bookService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void findById_shouldThrowIfNotFound() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.findById(1));
    }

    @Test
    void deleteById_shouldRemoveBookIfExists() {
        when(bookRepository.existsById(1)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1);

        assertDoesNotThrow(() -> bookService.deleteById(1));
    }

    @Test
    void deleteById_shouldThrowIfNotExists() {
        when(bookRepository.existsById(1)).thenReturn(false);

        assertThrows(BookNotFoundException.class, () -> bookService.deleteById(1));
    }

    @Test
    void update_shouldSaveAndReturnDto() {
        when(bookMapper.toEntity(bookRequest)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto result = bookService.update(bookRequest);

        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
    }
}

