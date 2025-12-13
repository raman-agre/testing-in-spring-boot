package com.test.integration_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;

@WebMvcTest(BookController.class)
public class ControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BookServiceImpl bookService;

    @Test
    void testGetBook() throws Exception {
        when(bookService.findBook(2)).thenReturn(new BookResponseDto("Outliers", "Malcolm Gladwell", 2));

        mockMvc.perform(get("/books/get/{id}", 2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.bookName").value("Outliers"))
                .andExpect(jsonPath("$.author").value("Malcolm Gladwell"))
                .andExpect(jsonPath("$.bookId").value(2));

    }

}
