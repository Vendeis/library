package com.example.library.book;

import com.example.library.author.Author;
import com.example.library.book.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book getBookById(long id);
    Book getBookByAuthor(Author Author);
    Book getAuthorByTitle(String title);
    List<Book> getAllBooks();
    void deleteBook(long id);
}
