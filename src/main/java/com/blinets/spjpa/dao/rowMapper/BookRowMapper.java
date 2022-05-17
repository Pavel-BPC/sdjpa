package com.blinets.spjpa.dao.rowMapper;

import com.blinets.spjpa.dao.AuthorDao;
import com.blinets.spjpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {
    @Autowired
    AuthorDao authorDao;

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .isbn(rs.getString("isbn"))
                .title(rs.getString("title"))
                .publisher(rs.getString("publisher"))
                .author(authorDao.getById(rs.getLong("author_id")))
                .build();
    }
}
