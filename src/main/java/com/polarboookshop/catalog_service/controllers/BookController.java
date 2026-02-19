package com.polarboookshop.catalog_service.controllers;

import com.polarboookshop.catalog_service.entities.Book;
import com.polarboookshop.catalog_service.sevices.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    @GetMapping("/books/{isbn}")
    public Book getByIsbn(@PathVariable final String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody final Book book) {
        System.out.println(book);
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("/books/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("/books/{isbn}")
    public Book put(@PathVariable final String isbn, @Valid @RequestBody final Book book) {
        return bookService.editBookDetails(isbn, book);
    }

}
