package com.springframework.spjpa;

import com.springframework.spjpa.domain.Book;
import com.springframework.spjpa.repository.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.springframework.spjpa.bootstrap"})
public class SpringBootJpaTest {
    @Autowired
    BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {
        long countAfter = bookRepository.count();
        assertThat(countAfter).isEqualTo(1);
        bookRepository.save(new Book("tittle 1", "isbn 2", "publisher"));
        long countBefore = bookRepository.count();
        assertThat(countAfter).isLessThan(countBefore);
    }

    @Order(2)
    @Test
    void testJpaTestTransaction() {
        long countAfter = bookRepository.count();
        assertThat(countAfter).isEqualTo(2);
    }
}