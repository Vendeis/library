package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceBean implements BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;
    private PublisherService publisherService;

    @Autowired
    public BookServiceBean(BookRepository bookRepository, AuthorService authorService, PublisherService publisherService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @Override
    public Book createBook(Book book) {
        if(bookRepository.findByTitle(book.getTitle()).isPresent())
            return null;
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(long id) {
        return findById(id);
    }

    @Override
    public Book getBookByAuthor(Author author) {
        return findByAuthor(author);
    }

    @Override
    public Book getAuthorByTitle(String title) {
        return findByTitle(title);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(long id) {
        Book book = findById(id);
        if(book != null)
            bookRepository.delete(book);
    }
    public Book findById(long id){
        Optional<Book> bookById = bookRepository.findById(id);
        if(bookById.isEmpty())
            return null;
        return bookById.get();
    }

    public Book findByAuthor(Author author){
        Optional<Book> bookByAuthor = bookRepository.findByAuthor(author);
        if(bookByAuthor.isEmpty())
            return null;
        return bookByAuthor.get();
    }

    public Book findByTitle(String title){
        Optional<Book> bookByTitle = bookRepository.findByTitle(title);
        if(bookByTitle.isEmpty())
            return null;
        return bookByTitle.get();
    }
}
