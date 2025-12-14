package com.test.integration_test.controller;

import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.service.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponseDto> getBookDetails(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.findBook(id));
    }

    @PostMapping("/add")
    public BookResponseDto addBookDetails(@RequestBody BookRequestDto bookRequestDto){
        return bookService.addBook(bookRequestDto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.findAllBooks());
    }

}
