package com.bluesky.restapi.service;

import com.bluesky.restapi.entity.Book;
import com.bluesky.restapi.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Optional<Book> getBookByName(String name) {
        return bookRepo.findByName(name);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepo.findAllByAuthor(author);
    }

    @Override
    public Book update(Integer id, Book update) {
        return bookRepo.findById(id).map(book -> {
            book.setName(update.getName());
            book.setAuthor(update.getAuthor());
            return bookRepo.save(book);
        }).orElseGet(() -> {
            update.setId(id);
            bookRepo.save(update);
            return update;
        });
    }

    @Override
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void delete(Integer id) {
        bookRepo.deleteById(id);
    }

}
