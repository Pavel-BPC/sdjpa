package com.springframework.spjpa.repository;

import com.springframework.spjpa.domain.Author;
import com.springframework.spjpa.domain.AuthorUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorUUIDRepository extends JpaRepository<AuthorUUID, UUID> {
}
