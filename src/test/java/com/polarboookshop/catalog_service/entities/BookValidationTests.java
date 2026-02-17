package com.polarboookshop.catalog_service.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        final var book = Book.of("1234567890", "title", "author", 9.90);
        final Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assert violations.isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        final var book = Book.of("123456789z", "title", "author", 9.90);
        final Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assert violations.size() == 1;
        assert violations.iterator().next().getMessage().equals("The ISBN format must be valid");
    }
}
