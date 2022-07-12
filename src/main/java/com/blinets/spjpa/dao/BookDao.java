package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Book;

import java.util.List;

public interface BookDao {
    Book saveBook(Book book);
    void deleteBookById(Long id);
    Book updateBook(Book book);
    List<Book> findByTitle(String title);
    Book findById(Long id);

    Book findByISBN(String isbn);
    List<Book> findAll();

    Book findBookByTitleCriteria(String title);

    Book findBookByTitleNative(String title);
}
