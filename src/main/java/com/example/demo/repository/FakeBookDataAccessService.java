package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Mongo")
public  class FakeBookDataAccessService implements BookDao{

    private static List<Book> DB = new ArrayList<>();

    @Override
    public int insertBook(UUID id, Book book) {
        DB.add(new Book(id, book.getName()));
        return 1;
    }

    @Override
    public List<Book> selectAllBooks() {
        return DB;
    }

    @Override
    public Optional<Book> selectBookById(UUID id) {
        return DB.stream()
                 .filter(book -> book.getId().equals(id))
                 .findFirst();
    }

    @Override
    public int deleteBookById(UUID id) {
        Optional<Book> optionalBook= selectBookById(id);
        if(optionalBook.isEmpty()) {
            return 0;
        }
        DB.remove(optionalBook.get());
        return 1;
    }

    @Override
    public int updateBookById(UUID id, Book update) {
        return selectBookById(id)
                .map(book -> {
                    int indexOfBookToUpdate = DB.indexOf(update);
                    if(indexOfBookToUpdate >= 0){
                        DB.set(indexOfBookToUpdate,new Book(id,update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


}
