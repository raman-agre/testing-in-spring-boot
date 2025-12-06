package com.test.integration_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private String bookName;
    private String authorName;
    private Integer bookId;

}
