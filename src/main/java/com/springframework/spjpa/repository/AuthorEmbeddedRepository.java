package com.springframework.spjpa.repository;

import com.springframework.spjpa.domain.compose.AuthorEmbedded;
import com.springframework.spjpa.domain.compose.IdName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, IdName> {
}
