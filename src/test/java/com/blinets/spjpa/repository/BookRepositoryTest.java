package com.blinets.spjpa.repository;

import com.blinets.spjpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.blinets.spjpa.repository")
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void findBookByTitleNullParameter() {
        assertNull(bookRepository.findBookByTitle(null));
    }

    @Test
    void findBookByTitleNoException() {
        assertNull(bookRepository.findBookByTitle("null"));
    }

    @Test
    void findAllByTitleNotNullTest() {
        Stream<Book> allByTitleNotNull = bookRepository.findAllByTitleNotNull();
        assertThat(allByTitleNotNull.count()).isGreaterThan(0);
    }

    @Test
    void queryByTitleTest() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookRepository.queryByTitle("Wolf in bowl");
        Book book = bookFuture.get();
        assertNotNull(book);
    }

    @Test
    void findBookByTitleWithQueryTest() {
        Book wolfInBowl = bookRepository.findBookByTitleWithQuery("Wolf in bowl");
        assertNotNull(wolfInBowl);
    }

    @Test
    void findBookByTitleWithQueryNamedTest() {
        Book wolfInBowl = bookRepository.findBookByTitleWithQueryNamed("Wolf in bowl");
        assertNotNull(wolfInBowl);
    }

    @Test
    void findBookByTitleWithQueryNativeTest() {
        Book wolfInBowl = bookRepository.findBookByTitleWithQueryNative("Wolf in bowl");
        assertNotNull(wolfInBowl);
    }

    @Test
    void jpaNameQueryTest() {
        Book wolfInBowl = bookRepository.jpaNameQuery("Wolf in bowl");
        assertNotNull(wolfInBowl);
    }
}