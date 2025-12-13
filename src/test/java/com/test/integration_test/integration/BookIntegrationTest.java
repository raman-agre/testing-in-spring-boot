package com.test.integration_test.integration;

import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testAddBook(){
        BookRequestDto bookRequestDto = new BookRequestDto("Sanjwaat", "VS Khandekar");
        String url = "/books/add";
        BookResponseDto bookResponseDto = testRestTemplate.postForObject(url, bookRequestDto, BookResponseDto.class);

        assertNotNull(bookResponseDto);
        assertEquals("Sanjwaat", bookResponseDto.getBookName());
        assertEquals("VS Khandekar", bookResponseDto.getAuthor());
    }

    @Test
    void testGetBook(){
        String url = "/books/get/{id}";
        BookResponseDto bookResponseDto = new BookResponseDto("Sanjwaat", "VS Khandekar", 6);
        ResponseEntity<BookResponseDto> entity =
                testRestTemplate.getForEntity(url, BookResponseDto.class, 6);

        assertNotNull(entity);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(bookResponseDto.getBookName(), entity.getBody().getBookName());
        assertEquals(bookResponseDto.getBookId(), entity.getBody().getBookId());
        assertEquals(bookResponseDto.getAuthor(), entity.getBody().getAuthor());
    }

    @Test
    void testGetAllBooks(){
        String url = "/books/get/all";
        ResponseEntity<BookResponseDto[]> entity = testRestTemplate.getForEntity(url, BookResponseDto[].class);
        assertNotNull(entity.getBody());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}
