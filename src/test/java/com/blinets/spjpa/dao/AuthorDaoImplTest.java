package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Author;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.blinets.spjpa")
class AuthorDaoImplTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void findByIdTest() {
        Author byId = authorDao.findById(1L);
        assertThat(byId).isNotNull();
    }

    @Test
    void deleteAuthorByIdTest() {
        Author author = new Author("firstName", "LastName");
        Author authorSave = authorDao.saveNewAuthor(author);
        assertThat(authorSave).isNotNull();

        authorDao.deleteAuthorById(authorSave.getId());

        assertThrows(EntityNotFoundException.class, () -> authorDao.findById(authorSave.getId()));
    }

    @Test
    void updateAuthorTest() {
        Author author = new Author("firstName", "N");
        Author authorSave = authorDao.saveNewAuthor(author);
        assertThat(authorSave).isNotNull();

        authorSave.setLastName("LastName");
        Author authorUpdate = authorDao.updateAuthor(authorSave);
        assertThat(authorUpdate).isNotNull();

        Author authorUpdated = authorDao.findById(authorSave.getId());
        assertThat(authorUpdated.getLastName()).isEqualTo(authorSave.getLastName());

    }

    @Test
    void saveNewAuthorTest() {
        Author author = authorDao.saveNewAuthor(new Author("Nikola", "Bogoga"));
        assertThat(author).isNotNull();
        Author byId = authorDao.findById(author.getId());
        assertThat(byId).isNotNull();
    }

    @Test
    void findByNameTest() {
        Author byName = authorDao.findByName("Pavel", "Blinets");
        assertThat(byName).isNotNull();
    }
}