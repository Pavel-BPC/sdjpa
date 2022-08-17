package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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
    void deleteAuthorByIdTest() {
        Author author = new Author("firstName", "LastName");
        Author authorSave = authorDao.saveNewAuthor(author);
        assertThat(authorSave).isNotNull();

        authorDao.deleteAuthorById(authorSave.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            authorDao.getById(authorSave.getId());
        });
    }

    @Test
    void updateAuthorTest() {
        Author author = new Author("firstName", "N");
        Author authorSave = authorDao.saveNewAuthor(author);
        assertThat(authorSave).isNotNull();

        authorSave.setLastName("LastName");
        Author authorUpdate = authorDao.updateAuthor(authorSave);
        assertThat(authorUpdate).isNotNull();

        Author authorUpdated = authorDao.getById(authorSave.getId());
        assertThat(authorUpdated.getLastName()).isEqualTo(authorSave.getLastName());

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

    @Test
    void findAuthorsByLastNameTest(){
        List<Author> authorsByLastName = authorDao.findAuthorsByLastName("Blinets",
                PageRequest.of(0, 10, Sort.by(Sort.Order.asc("lastName"))));
        assertThat(authorsByLastName).isNotNull();
        assertThat(authorsByLastName.size()).isEqualTo(10);
    }
}