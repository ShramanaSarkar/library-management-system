package com.lms.library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.library_management_system.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
