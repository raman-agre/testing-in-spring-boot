package com.test.integration_test.service;

import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto findBook(Integer id);
    BookResponseDto addBook(BookRequestDto bookRequestDto);
    List<BookResponseDto> findAllBooks();
}
