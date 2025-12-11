package com.test.integration_test.controller;

import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    BookServiceImpl bookService;

    @InjectMocks
    BookController bookController;

    @Test
    void testGetBookDetails(){
        Integer id = 1;
        BookResponseDto mockResponse = new BookResponseDto("Can't hurt me", "David Goggins", 1);
        when(bookService.findBook(id)).thenReturn(mockResponse);
        ResponseEntity<BookResponseDto> bookResponseDtoResponseEntity = bookController.getBookDetails(id);

        assertEquals(mockResponse.getBookName(), bookResponseDtoResponseEntity.getBody().getBookName());
        assertEquals(mockResponse.getAuthor(), bookResponseDtoResponseEntity.getBody().getAuthor());
        assertEquals(mockResponse.getBookId(), bookResponseDtoResponseEntity.getBody().getBookId());
    }

    @Test
    void testGetAllBooks(){
        BookResponseDto mockResponse = new BookResponseDto("Deep Work", "Cal Newport", 4);
        BookResponseDto mockResponse2 = new BookResponseDto("Outliers", "Malcolm Gladwell", 2);
        List<BookResponseDto> responseDtoList = new ArrayList<>();
        responseDtoList.add(mockResponse);
        responseDtoList.add(mockResponse2);

        when(bookService.findAllBooks()).thenReturn(responseDtoList);
        List<BookResponseDto> actualResponse = bookController.getAllBooks().getBody();

        assertEquals(responseDtoList, actualResponse);
        assertEquals(responseDtoList.get(0), actualResponse.get(0));
        assertEquals(responseDtoList.get(1), actualResponse.get(1));
    }

    @Test
    void testAddBookDetails(){
        BookRequestDto bookRequestDto = new BookRequestDto("Can't hurt me", "David Goggins");
        BookResponseDto bookResponseDto = new BookResponseDto("Can't hurt me", "David Goggins", 1);

        when(bookService.addBook(bookRequestDto)).thenReturn(bookResponseDto);
        BookResponseDto actualResponse = bookController.addBookDetails(bookRequestDto);

        assertEquals(bookResponseDto, actualResponse);
        assertEquals(bookResponseDto.getBookName(), actualResponse.getBookName());
        assertEquals(bookResponseDto.getBookId(), actualResponse.getBookId());
        assertEquals(bookResponseDto.getAuthor(), actualResponse.getAuthor());
    }

}
