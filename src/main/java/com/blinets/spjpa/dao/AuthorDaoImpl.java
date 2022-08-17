package com.blinets.spjpa.dao;

import com.blinets.spjpa.dao.rowMapper.AuthorExtractor;
import com.blinets.spjpa.dao.rowMapper.AuthorRowMapper;
import com.blinets.spjpa.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Author getById(Long id) {
        String sql = "select a.id as id, a.first_name, a.last_name, b.id as book_id, b.title, b.publisher, b.isbn\n" +
                "from author a\n" +
                "         left outer join book b on a.id = b.author_id\n" +
                "where a.id = ?";

        return jdbcTemplate.query(sql, new AuthorExtractor(), id);
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("select * from author where first_name = ? and last_name = ?",
                getRowMapper(), firstName, lastName);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        jdbcTemplate.update("INSERT Into author (first_name, last_name) values (?,?)", author.getFirstName(), author.getLastName());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.TYPE);
        return getById(id);
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update("UPDATE author set first_name = ?, last_name = ? where id = ?",
                author.getFirstName(), author.getLastName(), author.getId());
        return getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        jdbcTemplate.update("DELETE from author where id = ?", id);
    }

    @Override
    public List<Author> findAuthorsByLastName(String name, Pageable pageable) {
        String sql = "select * from author " +
                "where last_name = ? " +
                "order by  first_name " +
                pageable.getSort().getOrderFor("lastName").getDirection().name() +
                " limit ? offset ? ";
        return jdbcTemplate.query(sql, getRowMapper(),
                name,
                pageable.getPageSize(),
                pageable.getOffset());
    }

    private RowMapper<Author> getRowMapper() {
        return new AuthorRowMapper();
    }
}
