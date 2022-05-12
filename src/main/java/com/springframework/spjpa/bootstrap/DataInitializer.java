package com.springframework.spjpa.bootstrap;

import com.springframework.spjpa.domain.Author;
import com.springframework.spjpa.domain.Book;
import com.springframework.spjpa.repository.AuthorRepository;
import com.springframework.spjpa.repository.BookRepository;
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
    }
}
