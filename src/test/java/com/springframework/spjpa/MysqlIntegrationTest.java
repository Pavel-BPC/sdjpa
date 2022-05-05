package com.springframework.spjpa;

import com.springframework.spjpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan("com.springframework.spjpa.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MysqlIntegrationTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void mysqlIntegrationTest() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(1);
    }
}
