package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.blinets.spjpa.dao")
class AuthorDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void getByIdTest() {
        Author byId = authorDao.getById(1L);
        assertThat(byId).isNotNull();
    }

    @Test
    void saveNewAuthorTest() {
        Author author = authorDao.saveNewAuthor(new Author("Nikola", "Bogoga"));
        assertThat(author).isNotNull();
        Author byId = authorDao.getById(author.getId());
        assertThat(byId).isNotNull();
    }

    @Test
    void findByNameTest() {
        Author byName = authorDao.findByName("Pavel", "Blinets");
        assertThat(byName).isNotNull();
    }
}