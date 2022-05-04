package com.springframework.spjpa;

import com.springframework.spjpa.domain.Book;
import com.springframework.spjpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SpringBootJpaTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void testJpaTestSplice() {
        long countAfter = bookRepository.count();
        bookRepository.save(new Book("tittle 1", "isbn 2", "publisher"));
        long countBefore = bookRepository.count();
        assertThat(countAfter).isLessThan(countBefore);
    }
}