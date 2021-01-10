package com.example.demo.api;

import com.example.demo.model.Book;
import com.example.demo.utils.DataNotFoundError;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface BookControllerInterface {
    public  int addBook(@RequestBody(required = true) Book book);
    public List<Book> getAllBooks();
    public Book getBookById(UUID id) throws DataNotFoundError;
    public void deleteBookById(UUID id) throws DataNotFoundError;
    public void updateBookById(UUID id, Book book) throws DataNotFoundError;
}
