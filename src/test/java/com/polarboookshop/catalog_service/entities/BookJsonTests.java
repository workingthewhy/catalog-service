package com.polarboookshop.catalog_service.entities;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "Title", "Author", 9.90, "publisher");

        var jsonContent = json.write(book);
        var document = JsonPath.parse(jsonContent.getJson());

        assertThat(document.read("$.isbn", String.class)).isEqualTo(book.isbn());
        assertThat(document.read("$.title", String.class)).isEqualTo(book.title());
        assertThat(document.read("$.author", String.class)).isEqualTo(book.author());
        assertThat(document.read("$.price", Double.class)).isEqualTo(book.price());

    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                  "isbn" : "1234567890",
                  "title" : "Title",
                  "author" : "Author",
                  "price" : 9.90
                }
                """;

        var book = json.parse(content).getObject();

        assertThat(book).usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90, null));
    }


}
