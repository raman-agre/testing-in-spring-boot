package com.test.integration_test.service;

import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.entity.Book;
import com.test.integration_test.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;


    @Test
    void findBook_shouldReturnValidResponse(){
        BookResponseDto mockBookResponse = new BookResponseDto("Head First Java", "Siera", 3);

        Mockito.when(bookRepository.findById(3)).thenReturn(mockBookResponse);

       BookResponseDto result = bookService.findBook(3);
       assertEquals("Head First Java", result.getBookName());
       assertEquals("Siera", result.getAuthor());
       assertEquals(3, result.getBookId());
       assertEquals(mockBookResponse, result);
    }

    @Test
    void addBook_shouldAddBook(){
       Book book = new Book(null, "Outliers", "Malcolm Gladwell");
       Mockito.doNothing().when(bookRepository).update(book);

       BookRequestDto bookRequestDto = new BookRequestDto("Outliers", "Malcolm Gladwell");
       BookResponseDto actualResponseDto = bookService.addBook(bookRequestDto);

       BookResponseDto mockResponse = new BookResponseDto("Outliers", "Malcolm Gladwell", null);

       assertEquals(mockResponse.getBookName(), actualResponseDto.getBookName());
       assertEquals(mockResponse.getAuthor(), actualResponseDto.getAuthor());
       assertEquals(mockResponse, actualResponseDto);
    }

}
