package com.springframework.spjpa.bootstrap;

import com.springframework.spjpa.domain.Author;
import com.springframework.spjpa.domain.AuthorUUID;
import com.springframework.spjpa.domain.Book;
import com.springframework.spjpa.domain.BookUUID;
import com.springframework.spjpa.repository.AuthorRepository;
import com.springframework.spjpa.repository.AuthorUUIDRepository;
import com.springframework.spjpa.repository.BookRepository;
import com.springframework.spjpa.repository.BookUUIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorUUIDRepository authorUUIDRepository;
   @Autowired
    private BookUUIDRepository bookUUIDRepository;

    @Override
    public void run(String... args) throws Exception {
        authorRepository.deleteAll();
        Author author = new Author("first name", "last name");
        authorRepository.save(author);
        authorRepository.findAll().forEach(System.out::println);

        bookRepository.deleteAll();
        Book book = new Book("title_1", "isbn_1", "publisher_1", null);
        bookRepository.save(book);
        bookRepository.findAll().forEach(System.out::println);

        bookUUIDRepository.deleteAll();
        BookUUID bookUUID = new BookUUID("title_1_uuid", "isbn_1_uuid", "publisher_1_uuid", null);
        bookUUIDRepository.save(bookUUID);
        bookUUIDRepository.findAll().forEach(System.out::println);

        authorUUIDRepository.deleteAll();
        AuthorUUID authorUUID = new AuthorUUID("first name UUID", "last name UUID");
        authorUUIDRepository.save(authorUUID);
        authorUUIDRepository.findAll().forEach(System.out::println);
    }
}
