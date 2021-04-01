package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Publisher;

import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book getBookById(long id);
    Book getBookByAuthor(Author Author);
    Book getAuthorByTitle(String title);
    List<Book> getAllBooks();
    void deleteBook(long id);
}
