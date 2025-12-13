package com.test.integration_test.controller;

import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.entity.Book;
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
        System.out.println("Post controller called!!!");
        return bookService.addBook(bookRequestDto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
//        List<BookResponseDto> responseDtoList =
        return ResponseEntity.ok(bookService.findAllBooks());
    }

//    @PostMapping("add/dto")
//    public ResponseEntity<BookResponseDto> getAllWithDTO(@RequestBody Book book){
//        return ResponseEntity.ok(bookService.addDto());
//    }

}
