package com.test.integration_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRequestDto {

    private String book;
    private String author;

    public BookRequestDto(String book, String author){
        System.out.println("BookRequestDto constructor called!!!");
        this.book = book;
        this.author = author;
    }
}
