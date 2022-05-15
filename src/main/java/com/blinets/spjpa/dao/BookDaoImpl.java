package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthorDao authorDao;

    @Override
    public Book saveBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("insert into book (isbn, publisher, title, author_id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getPublisher());
            preparedStatement.setString(3, book.getTitle());
            if (book.getAuthor() != null) {
                preparedStatement.setLong(4, book.getAuthor().getId());
            }
            else {
                preparedStatement.setNull(4, Types.BIGINT);
            }
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                return findById(resultSet.getLong(1));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("delete from book where book.id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Book updateBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("update book set isbn = ?, publisher = ?, title = ?, author_id = ? where book.id = ?");
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getPublisher());
            preparedStatement.setString(3, book.getTitle());
            if(book.getAuthor() != null) {
                preparedStatement.setLong(4, book.getAuthor().getId());
            }
            preparedStatement.setLong(5, book.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return findById(book.getId());
    }

    @Override
    public List<Book> findByTitle(String title) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from book where title = ?");
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Book findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from book where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setTitle(resultSet.getString("title"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setAuthor(authorDao.getById(resultSet.getLong("author_id")));
        book.setId(resultSet.getLong("id"));
        return book;
    }
}
