package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void saveBookTest() throws SQLException {
        Book book = bookDao.saveBook(new Book("title saveBookTest", "isbn saveBookTest",
                "publisher saveBookTest", authorDao.getById(1L)));
        assertThat(book).isNotNull();
    }

    @Test
    void deleteBookByIdTest() {
        Book book = bookDao.saveBook(new Book("title deleteBookById", "isbn deleteBookById",
                "publisher deleteBookById", authorDao.getById(1L)));
        assertThat(book).isNotNull();

        bookDao.deleteBookById(book.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            bookDao.findById(book.getId());
        });
    }

    @Test
    void updateBookTest() {
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

    }

    @Test
    void findByTitleTest() {
        Book book = bookDao.saveBook(new Book("title findByTitle", "isbn findByTitle", "publisher findByTitle", authorDao.getById(1L)));
        assertThat(book).isNotNull();
        List<Book> byTitle = bookDao.findByTitle("Wolf in bowl");
        assertThat(byTitle.isEmpty()).isEqualTo(false);
    }

    @Test
    void findByIdTest() {
        Book book = bookDao.findById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    void findAllBooksTest() {
        List<Book> allBooks = bookDao.findAllBooks();
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isGreaterThan(10);
    }

    @Test
    void findAllBooksPage_1_Test() {
        List<Book> allBooks = bookDao.findAllBooks(10, 0);
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(10);
    }

    @Test
    void findAllBooksPage_2_Test() {
        List<Book> allBooks = bookDao.findAllBooks(10, 10);
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(10);
    }

    @Test
    void findAllBooksPage_10_Test() {
        List<Book> allBooks = bookDao.findAllBooks(10, 100);
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(0);
    }

    @Test
    void findAllBooksPage_1_pageable_Test() {
        List<Book> allBooks = bookDao.findAllBooks(PageRequest.of(0, 10));
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(10);
    }

    @Test
    void findAllBooksPage_2_pageable_Test() {
        List<Book> allBooks = bookDao.findAllBooks(PageRequest.of(1, 10));
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(10);
    }

    @Test
    void findAllBooksPage_10_pageable_Test() {
        List<Book> allBooks = bookDao.findAllBooks(PageRequest.of(10, 10));
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(0);
    }
    @Test
    void findAllBooksPage_1_sortByTitle_Test() {
        List<Book> allBooks = bookDao.findAllBooksSortByTitle(PageRequest.of(1, 10,
                Sort.by(Sort.Order.desc("title"))));
        assertThat(allBooks).isNotNull();
        assertThat(allBooks.size()).isEqualTo(10);
    }
}