package com.blinets.spjpa.dao;

import com.blinets.spjpa.dao.rowMapper.BookRowMapper;
import com.blinets.spjpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookRowMapper bookRowMapper;

    @Override
    public Book saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO book (isbn, publisher, title, author_id) values (?,?,?,?)",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthor().getId());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.TYPE);
        return findById(id);
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update("DELETE FROM book where id = ?", id);
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update("UPDATE book SET isbn = ?, publisher = ? , title = ? , author_id = ? where id = ?",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthor().getId(), book.getId());
        return findById(book.getId());
    }

    @Override
    public List<Book> findByTitle(String title) {
        return jdbcTemplate.query(
                "select * from book where title = ?",
                new Object[]{title},
                bookRowMapper
        );
    }

    @Override
    public Book findById(Long id) {
        return jdbcTemplate.queryForObject("select * from book where id = ?", bookRowMapper, id);
    }

}
