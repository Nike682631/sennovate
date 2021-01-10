package com.example.demo.api;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.utils.DataNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
public class BookController implements BookControllerInterface{
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public int  addBook(@NonNull @RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping()
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(path = "{id}")
    public Book getBookById(@PathVariable("id") UUID id) throws DataNotFoundError {
        return bookService.getBookById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBookById(@PathVariable("id") UUID id) throws DataNotFoundError {
        bookService.deleteBookById(id);
    }

    @PutMapping(path = "{id}")
    public void updateBookById(@PathVariable("id") UUID id,@NonNull @RequestBody Book bookToUpdate) throws DataNotFoundError {
        bookService.updateBookById(id, bookToUpdate);
    }

}
