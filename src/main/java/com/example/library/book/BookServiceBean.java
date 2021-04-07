package com.example.library.book;

import com.example.library.author.Author;
import com.example.library.author.AuthorService;
import com.example.library.book.Book;
import com.example.library.book.BookService;
import com.example.library.publisher.Publisher;
import com.example.library.publisher.PublisherService;
import com.example.library.book.BookRepository;
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
        Book newBook = new Book();
        Author author = book.getAuthor();
        String authorName = author.getName();

        if (bookRepository.findByAuthorNameAndTitle(authorName, book.getTitle()).isPresent())
            return null;

        Book preparedBook = prepareBookToProcess(book, newBook, authorName);
        return bookRepository.save(preparedBook);
    }

    private Book prepareBookToProcess(Book processingBook, Book bookToSave, String authorName) {

        setBookAuthorFromDBorCreateNewIfNotExists(bookToSave, authorName);
        setBookPublisherFromDBorCreateNewIfNotExists(processingBook, bookToSave);

        bookToSave.setTitle(processingBook.getTitle());
        bookToSave.setDescription(processingBook.getDescription());


        return bookToSave;
    }


    private void setBookAuthorFromDBorCreateNewIfNotExists(Book bookToSave, String authorName) {
        Author authorByName = authorService.getAuthorByName(authorName);
        if(authorByName != null) {
            bookToSave.setAuthor(authorByName);
        }
        else{
        Author newAuthor = new Author(authorName);
        bookToSave.setAuthor(newAuthor);
        authorService.createAuthor(newAuthor);
        }
    }
    private void setBookPublisherFromDBorCreateNewIfNotExists(Book processingBook, Book bookToSave) {

        Publisher publisher = processingBook.getPublisher();

        String name = publisher.getName();
        String language = publisher.getLanguage();
        int publications = publisher.getPublications();
        String city = publisher.getCity();

        Publisher publisher2 = publisherService.getPublisherByNameAndLanguage(name,language);


        if(publisher2 != null) {
            bookToSave.setPublisher(publisher2);
        }
        else{
            System.out.println("publisher2 is null");
            Publisher publisher3 = new Publisher(name, publications, city, language);
            bookToSave.setPublisher(publisher3);
            publisherService.createPublisher(publisher3);
        };

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
