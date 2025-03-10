package com.lms.library_management_system.service;

import com.lms.library_management_system.dao.AddressDao;
import com.lms.library_management_system.dao.BookDao;
import com.lms.library_management_system.dao.UserDao;
import com.lms.library_management_system.dto.BookDto;
import com.lms.library_management_system.entity.Address;
import com.lms.library_management_system.entity.Book;
import com.lms.library_management_system.entity.User;
import com.lms.library_management_system.util.ResponseStructure;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<ResponseStructure<Book>> saveBook(BookDto bookDto, int userId) {

        User dbUser= userDao.findByUserId(userId);
        Book book = modelMapper.map(bookDto, Book.class);

        book.setUser(dbUser);
        Book savedBook = bookDao.saveBook(book);

        ResponseStructure<Book> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Book Saved");
        response.setData(savedBook);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Book>> findBookById(int bookId) {
        Book dbBook = bookDao.findByBookId(bookId);
        ResponseStructure<Book> response = new ResponseStructure<>();

        if (dbBook != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Book Found");
            response.setData(dbBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Book Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseStructure<Book>> updateBook(int bookId, BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        Book updatedBook = bookDao.updateBook(bookId, book);

        ResponseStructure<Book> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Book Updated");
        response.setData(updatedBook);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Book>> deleteBook(int bookId) {
        Book deletedBook = bookDao.deleteBook(bookId);
        ResponseStructure<Book> response = new ResponseStructure<>();

        if (deletedBook != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Book Deleted");
            response.setData(deletedBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Book Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseStructure<List<Book>>> fetchAllBooks() {
        List<Book> books = bookDao.fetchAllBook();
        ResponseStructure<List<Book>> response = new ResponseStructure<>();

        if (!books.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Books Retrieved");
            response.setData(books);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("No Books Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
