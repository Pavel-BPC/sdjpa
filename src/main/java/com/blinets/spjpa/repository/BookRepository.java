package com.blinets.spjpa.repository;

import com.blinets.spjpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByTitle(String title);

    @Nullable
    Book findBookByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Async
    Future<Book> queryByTitle(String title);

    @Query("select b from Book b where b.title = ?1")
    Book findBookByTitleWithQuery(String title);

    @Query("select b from Book b where b.title = :title")
    Book findBookByTitleWithQueryNamed(@Param("title") String title);

    @Query(value = "select * from book where title = :title", nativeQuery = true)
    Book findBookByTitleWithQueryNative(@Param("title") String title);

    Book jpaNameQuery(@Param("title") String title);

}
