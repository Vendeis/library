package com.example.library.book;

import com.example.library.author.Author;
import com.example.library.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    Optional<Book> findByAuthor(Author author);

    Optional<Book> findByAuthorNameAndTitle(String authorName, String title);
}
