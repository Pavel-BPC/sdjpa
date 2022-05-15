package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {
    @Override
    public Book saveBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }
}
