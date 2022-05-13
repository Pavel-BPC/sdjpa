package com.springframework.spjpa.repository;

import com.springframework.spjpa.domain.BookUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookUUIDRepository extends JpaRepository<BookUUID, Long> {
}
