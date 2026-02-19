package com.polarboookshop.catalog_service;

import com.polarboookshop.catalog_service.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostThenBookCreated() {
		var isbn = "1111111111";
		var expectedBook = Book.of(isbn, "Title", "Author", 9.90, "publisher");

		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(actualBook -> {
					assert actualBook != null;
					assert actualBook.isbn().equals(expectedBook.isbn());
				});

		// Remove the database entry added above.
		webTestClient
				.delete()
				.uri("/books/" + isbn)
				.exchange();
	}

}