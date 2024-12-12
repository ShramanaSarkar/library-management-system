package com.lms.library_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lms.library_management_system.entity.Book;
import com.lms.library_management_system.repository.BookRepository;

@Repository
public class BookDao {
	@Autowired
	private BookRepository bookRepository;
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	public Book updateBook(int bookId,Book book) {
		Optional<Book> optional=bookRepository.findById(bookId);
		if(optional.isPresent()) {
			book.setBookId(bookId);
			bookRepository.save(book);
			return optional.get();
		}
		return null;
	}
	
	public Book findByBookId(int bookId) {
		Optional<Book> optional=bookRepository.findById(bookId);
		if(optional.isPresent()) 
			return optional.get();
		return null;
	}
	
	public Book deleteBook(int bookId) {
		Optional<Book> optional=bookRepository.findById(bookId);
		if(optional.isPresent()) { 
			bookRepository.delete(optional.get());
			return optional.get();
		}
		return null;
	}
	
	public List<Book> fetchAllBook(){
		return bookRepository.findAll();
	}

}
