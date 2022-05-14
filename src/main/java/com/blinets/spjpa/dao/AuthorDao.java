package com.blinets.spjpa.dao;

import com.blinets.spjpa.domain.Author;

public interface AuthorDao {
    Author getById(Long id);
}
