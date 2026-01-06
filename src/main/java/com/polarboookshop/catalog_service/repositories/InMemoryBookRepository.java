package com.polarboookshop.catalog_service.repositories;

import com.polarboookshop.catalog_service.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(final String isbn) {
        return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) :
                Optional.empty();
    }

    @Override
    public boolean existsByIsbn(final String isbn) {
        return books.get(isbn) != null;
    }

    @Override
    public Book save(final Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(final String isbn) {
        books.remove(isbn);
    }

}
