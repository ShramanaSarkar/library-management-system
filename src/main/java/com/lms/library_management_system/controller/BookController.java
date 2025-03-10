package com.lms.library_management_system.controller;

import com.lms.library_management_system.dto.BookDto;
import com.lms.library_management_system.entity.Book;
import com.lms.library_management_system.service.BookService;
import com.lms.library_management_system.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/{userId}")
    public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody BookDto book, @PathVariable int userId) {
        return bookService.saveBook(book, userId);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseStructure<Book>> findBookById(@PathVariable int bookId) {
        return bookService.findBookById(bookId);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ResponseStructure<Book>> updateBook(@PathVariable int bookId, @RequestBody BookDto book) {
        return bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ResponseStructure<Book>> deleteBook(@PathVariable int bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Book>>> fetchAllBooks() {
        return bookService.fetchAllBooks();
    }
}
