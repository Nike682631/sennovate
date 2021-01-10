package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookDao;
import com.example.demo.utils.DataNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(@Qualifier("Mongo") BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public int addBook(Book book){
        return bookDao.insertBook(book);
    }

    public List<Book> getAllBooks(){
        return bookDao.selectAllBooks();
    }

    public Optional<Book> getBookById(UUID id) throws DataNotFoundError  {
        return bookDao.selectBookById(id);
    }

    public int deleteBookById(UUID id) throws  DataNotFoundError{
        return bookDao.deleteBookById(id);
    }

    public int updateBookById(UUID id,Book newBook) throws DataNotFoundError{
        return bookDao.updateBookById(id,newBook);
    }
}
