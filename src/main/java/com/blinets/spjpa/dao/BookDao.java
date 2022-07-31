package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {
    Book saveBook(Book book);
    void deleteBookById(Long id);
    Book updateBook(Book book);
    List<Book> findByTitle(String title);
    Book findById(Long id);

    List<Book> findAllBooks();
    List<Book> findAllBooks(int pageSize, int offset);
    List<Book> findAllBooks(Pageable pageable);
    List<Book> findAllBooksSortByTitle(Pageable pageable);
}
