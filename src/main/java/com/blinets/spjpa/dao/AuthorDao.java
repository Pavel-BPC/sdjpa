package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Author;

public interface AuthorDao {
    Author findById(Long id);

    Author findByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
