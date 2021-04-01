package com.example.library.service;

import com.example.library.model.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);
    Author getAuthorById(long id);
    Author getAuthorByName(String name);
    List<Author> getAllAuthors();
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}
