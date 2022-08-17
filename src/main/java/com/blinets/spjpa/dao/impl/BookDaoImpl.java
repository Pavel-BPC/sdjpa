package com.blinets.spjpa.dao.impl;

import com.blinets.spjpa.dao.BookDao;
import com.blinets.spjpa.domain.Book;
import com.blinets.spjpa.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        if (bookOptional.isPresent()) {
            Book book1 = bookOptional.get();
            book1.setIsbn(book.getIsbn());
            book1.setTitle(book.getTitle());
            book1.setPublisher(book.getPublisher());
            book1.setAuthorId(book.getAuthorId());
            return bookRepository.save(book1);

        }
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        PageRequest pageRequest = PageRequest.ofSize(pageSize);
        if (offset > 0) {
            pageRequest = pageRequest.withPage(offset / pageSize);
        } else {
            pageRequest = pageRequest.withPage(offset);
        }
        return findAllBooks(pageRequest);
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.getContent();
    }
}
