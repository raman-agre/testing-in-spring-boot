package com.test.integration_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.integration_test.dto.BookRequestDto;
import com.test.integration_test.dto.BookResponseDto;
import com.test.integration_test.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.bookName").value("Outliers"))
                .andExpect(jsonPath("$.author").value("Malcolm Gladwell"))
                .andExpect(jsonPath("$.bookId").value(2));

    }

    @Test
    void testGetAllBooks() throws Exception {
        List<BookResponseDto> list = new ArrayList<>();
        list.add(new BookResponseDto("Can't hurt me", "David Goggins", 1));
        list.add(new BookResponseDto("Outliers", "Malcolm Gladwell", 2));
        list.add(new BookResponseDto("Head First Java", "Siera", 3));
        list.add(new BookResponseDto("DeepWork", "Cal Newport", 4));

        when(bookService.findAllBooks()).thenReturn(list);

        String url = "/books/get/all";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].bookName").value("Can't hurt me"))
                .andExpect(jsonPath("$[0].author").value("David Goggins"))
                .andExpect(jsonPath("$[0].bookId").value(1));

    }

    @Test
    void testAddBook() throws Exception {
        BookRequestDto bookRequestDto = new BookRequestDto("Shoe Dog", "Phil Knight");
        when(bookService.addBook(bookRequestDto)).thenReturn(new BookResponseDto("Shoe Dog", "Phil Knight", 5));
        String url = "/books/add";
        mockMvc.perform(post(url)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequestDto)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(jsonPath("$.bookName").value("Shoe Dog"))
                        .andExpect(jsonPath("$.author").value("Phil Knight"));
    }

}
