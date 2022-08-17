package com.blinets.spjpa.dao.impl;

import com.blinets.spjpa.dao.AuthorDao;
import com.blinets.spjpa.domain.Author;
import com.blinets.spjpa.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {
    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        if (optionalAuthor.isPresent()) {
            Author authorDb = optionalAuthor.get();
            authorDb.setFirstName(author.getFirstName());
            authorDb.setLastName(author.getLastName());
            return authorRepository.save(authorDb);
        }
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findAuthorsByLastName(String name, Pageable pageable) {
        return authorRepository.findAuthorsByLastName(name, pageable).getContent();
    }
}
