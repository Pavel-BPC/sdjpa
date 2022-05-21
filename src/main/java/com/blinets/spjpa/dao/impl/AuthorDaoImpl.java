package com.blinets.spjpa.dao.impl;

import com.blinets.spjpa.dao.AuthorDao;
import com.blinets.spjpa.domain.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }
}
