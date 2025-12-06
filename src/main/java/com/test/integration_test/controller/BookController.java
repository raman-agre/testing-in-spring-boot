package com.test.integration_test.controller;

import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.entity.Book;
import com.test.integration_test.service.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class BookController {

    BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponseDto> getBookDetails(@PathVariable Integer id){
        Book book = bookService.getBookById(id);
        BookResponseDto bookResponseDto = new BookResponseDto(book.getBook(), book.getAuthor(), book.getId());
        return ResponseEntity.ok(bookResponseDto);
    }

}
