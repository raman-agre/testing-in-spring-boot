package com.test.integration_test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponseDto {

    private String bookName;
    private String author;
    private Integer bookId;

    public BookResponseDto(String bookName, String author, Integer bookId){
        System.out.println("BookResponseDto constructor called!!!");
        this.bookName = bookName;
        this.author = author;
        this.bookId = bookId;
    }

}
