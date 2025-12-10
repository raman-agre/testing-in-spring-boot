package com.test.integration_test.service;

import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.entity.Book;
import com.test.integration_test.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookResponseDto findBook(Integer id){
        if(id==null) throw new IllegalArgumentException("Book ID cannot be null");
        return bookRepository.findById(id);
    }

    public BookResponseDto addBook(BookRequestDto bookRequestDto){
        Book book = new Book();
        book.setBook(bookRequestDto.getBook());
        book.setAuthor(bookRequestDto.getAuthor());
        bookRepository.update(book);
        return new BookResponseDto(book.getBook(), book.getAuthor(), book.getId());
    }

    public List<BookResponseDto> findAllBooks(){
        return bookRepository.findAll();
    }

}
