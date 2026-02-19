package com.polarboookshop.catalog_service.repositories;

import com.polarboookshop.catalog_service.config.DataConfig;
import com.polarboookshop.catalog_service.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    //@Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234567891";
        var book = Book.of(bookIsbn, "Title", "Author", 12.90, "publisher");
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assert actualBook.isPresent();
        assert actualBook.get().isbn().equals(bookIsbn);

    }
}
