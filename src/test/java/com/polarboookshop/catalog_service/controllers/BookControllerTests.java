package com.polarboookshop.catalog_service.controllers;

import com.polarboookshop.catalog_service.exceptions.BookNotFoundException;
import com.polarboookshop.catalog_service.sevices.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        final String isbn = "1234567890";
        Mockito.when(bookService.viewBookDetails(isbn)).thenThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books" + isbn)).andExpect(status().isNotFound());
    }

}
