package com.blinets.spjpa.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NamedQueries({
        @NamedQuery(name = "find_all_book",query = "from Book"),
        @NamedQuery(name = "find_book_by_title",query = "from Book b where b.title = :title")
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private String publisher;

    private Long authorId;

    public Book(String title, String isbn, String publisher, Long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
