//In this module we will be primarily interacting with the database

package com.example.demo.repository;

import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookDao {

    int insertBook(UUID id , Book book );

    default int insertBook(Book book){
        //This randomly generates an id for books
        UUID id = UUID.randomUUID();
        return insertBook(id, book);
    }

    List<Book> selectAllBooks();

    Optional<Book> selectBookById(UUID id);

    int deleteBookById(UUID id);

    int updateBookById(UUID id, Book book);
}
