package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public List<Book> viewAll(Model model) {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book viewBook(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
}
