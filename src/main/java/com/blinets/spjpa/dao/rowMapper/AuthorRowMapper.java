package com.blinets.spjpa.dao.rowMapper;

import com.blinets.spjpa.domain.Author;
import com.blinets.spjpa.domain.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = Author.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .build();
        return author;
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        return Book.builder()
                .id(rs.getLong(4))
                .title(rs.getString(5))
                .publisher(rs.getString(6))
                .isbn(rs.getString(7))
                .build();
    }

    public Author mapRowBook(ResultSet rs, int rowNum) throws SQLException {
        if (rs.next()) {

            Author author = Author.builder()
                    .id(rs.getLong("id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .build();

            if (rs.getString("isbn") != null) {
                author.setBooks(new ArrayList<>());
                author.getBooks().add(mapBook(rs));
            }
            while (rs.next()) {
                author.getBooks().add(mapBook(rs));
            }

            return author;
        }
        throw new EmptyResultDataAccessException(1);
    }
}
