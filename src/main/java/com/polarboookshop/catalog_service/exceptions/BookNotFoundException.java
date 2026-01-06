package com.polarboookshop.catalog_service.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final String isbn) {
        super("The book with ISBN: " + isbn + "was not found.");
    }
}
