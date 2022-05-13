package com.blinets.spjpa;

import com.blinets.spjpa.domain.Book;
import com.blinets.spjpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void bookRepositoryTest() {
        Book build = Book.builder().isbn("esbn").title("title").publisher(null).build();
        Book save = bookRepository.save(build);
        assertThat(save).isNotNull();
        long count = bookRepository.count();
        assertThat(count).isGreaterThan(0);
    }
}
