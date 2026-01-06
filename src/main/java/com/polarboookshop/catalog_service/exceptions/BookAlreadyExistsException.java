package com.polarboookshop.catalog_service.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(final String isbn) {
        super("A book with ISBN: " + isbn + "already exists.");
    }
}
