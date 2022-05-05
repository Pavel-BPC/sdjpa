package com.springframework.spjpa.bootstrap;

import com.springframework.spjpa.domain.Book;
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

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        Book book = new Book("title_1", "isbn_1", "publisher_1");
        Book save = bookRepository.save(book);
        System.out.println("Book id - " + save.getId());
        bookRepository.findAll().forEach(book1 -> {
            System.out.println(book1);
        });
    }
}
