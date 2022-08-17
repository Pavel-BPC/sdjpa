package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorDao {
    List<Author> findAll();

    Author findById(Long id);

    Author findByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    List<Author> getListAuthorByLastNameLike(String lastName);

    Author findByNameCriteria(String firstName, String lastName);

    Author findByNameNative(String firstName, String lastName);

    List<Author> findAuthorsByLastName(String name, Pageable pageable);

}
