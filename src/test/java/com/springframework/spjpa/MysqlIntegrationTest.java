package com.springframework.spjpa;

import com.springframework.spjpa.domain.AuthorUUID;
import com.springframework.spjpa.domain.BookNatural;
import com.springframework.spjpa.domain.BookUUID;
import com.springframework.spjpa.domain.compose.AuthorCompose;
import com.springframework.spjpa.domain.compose.IdName;
import com.springframework.spjpa.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan("com.springframework.spjpa.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MysqlIntegrationTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookUUIDRepository bookUUIDRepository;

    @Autowired
    AuthorUUIDRepository authorUUIDRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorComposeRepository authorComposeRepository;

    @Test
    void authorComposeTest() {
        IdName idName = new IdName();
        idName.setFirstName("firstName id");
        idName.setLastName("lastName id");
        AuthorCompose authorCompose = new AuthorCompose();
        authorCompose.setCountry("BY");
        authorCompose.setFirstName(idName.getFirstName());
        authorCompose.setLastName(idName.getLastName());

        AuthorCompose save = authorComposeRepository.save(authorCompose);
        assertThat(save).isNotNull();

        AuthorCompose fetched = authorComposeRepository.getById(idName);
        assertThat(fetched).isNotNull();
    }

    @Test
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("title");
        BookNatural save = bookNaturalRepository.save(bookNatural);
        BookNatural fetched = bookNaturalRepository.getById(save.getTitle());
        assertThat(fetched).isNotNull();
    }

    @Test
    void mysqlIntegrationTest() {
        long countBook = bookRepository.count();
        assertThat(countBook).isEqualTo(1);

        long countBookUUID = bookUUIDRepository.count();
        assertThat(countBookUUID).isEqualTo(1);

        long countAuthorUUID = authorUUIDRepository.count();
        assertThat(countAuthorUUID).isEqualTo(1);

        long countAuthor = authorRepository.count();
        assertThat(countAuthor).isEqualTo(1);
    }

    @Test
    void testUuidOperation() {
        List<UUID> collectAuthorUUID = authorUUIDRepository.findAll().stream().map(AuthorUUID::getId).collect(Collectors.toList());
        assertThat(collectAuthorUUID).isNotNull();

        List<UUID> collectBookUUID = bookUUIDRepository.findAll().stream().map(BookUUID::getId).collect(Collectors.toList());
        assertThat(collectBookUUID).isNotNull();

    }

    @Test
    void testBookUuid() {
        BookUUID bookUuid = bookUUIDRepository.save(new BookUUID());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId());

        BookUUID fetched = bookUUIDRepository.getById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorUuid() {
        AuthorUUID authorUuid = authorUUIDRepository.save(new AuthorUUID());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        AuthorUUID fetched = authorUUIDRepository.getById(authorUuid.getId());
        assertThat(fetched).isNotNull();
    }
}