package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.blinets.spjpa.dao")
class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Autowired
    AuthorDao authorDao;
    @Test
    void saveBook() {
        Book byId = bookDao.findById(1L);
        assertThat(byId).isNotNull();
    }

    @Test
    void deleteBookById() {

        Book book = bookDao.saveBook(new Book("title deleteBookById", "isbn deleteBookById",
                "publisher deleteBookById", null));
        assertThat(book).isNotNull();

        bookDao.deleteBookById(book.getId());

        Book byId = bookDao.findById(book.getId());
        assertThat(byId).isNull();
    }

    @Test
    void updateBook() {
        Book book = bookDao.saveBook(new Book("title updateBook", "isbn updateBook",
                "publisher updateBook", authorDao.getById(1L)));
        assertThat(book).isNotNull();

        book.setIsbn("1");
        book.setTitle("1");
        book.setPublisher("1");

        Book bookUpdate = bookDao.updateBook(book);
        assertThat(bookUpdate.getIsbn()).isEqualTo(book.getIsbn());
        assertThat(bookUpdate.getPublisher()).isEqualTo(book.getPublisher());
        assertThat(bookUpdate.getTitle()).isEqualTo(book.getTitle());

        bookDao.deleteBookById(book.getId());
    }

    @Test
    void findByTitle() {
        Book book = bookDao.saveBook(new Book("title findByTitle", "isbn findByTitle", "publisher findByTitle", null));
        assertThat(book).isNotNull();

        List<Book> byTitle = bookDao.findByTitle(book.getTitle());
        assertThat(!byTitle.isEmpty()).isEqualTo(true);

        bookDao.deleteBookById(book.getId());
    }

    @Test
    void findById() {
        Book book = bookDao.saveBook(new Book("title findById", "isbn findById", "publisher findById", null));
        assertThat(book).isNotNull();

        Book bookFetch = bookDao.findById(book.getId());
        assertThat(bookFetch).isEqualTo(book);

        bookDao.deleteBookById(book.getId());
    }

}