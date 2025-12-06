package com.test.integration_test.service;

import com.test.integration_test.entity.Book;
import com.test.integration_test.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Integer id){
        if(id==null) throw new IllegalArgumentException("Book ID cannot be null");
        return bookRepository.findById(id);
    }
}
