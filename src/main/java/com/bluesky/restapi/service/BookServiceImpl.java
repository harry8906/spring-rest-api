package com.bluesky.restapi.service;

import com.bluesky.restapi.exception.ResourceNotFoundException;
import com.bluesky.restapi.entity.Book;
import com.bluesky.restapi.repo.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        log.info("Book update request for id {} ", id);
        return bookRepo.findById(id).map(book -> {
            book.setName(update.getName());
            book.setAuthor(update.getAuthor());
            Book savedBook = bookRepo.save(book);
            log.info("Book update request - successful");
            return savedBook;
        }).orElseGet(() -> {
            update.setId(id);
            bookRepo.save(update);
            log.info("Book update request - record not found with id {}, added new entry", id);
            return update;
        });
    }

    @Override
    public Book addBook(Book book) {
        if (book.getId() != null) throw new IllegalArgumentException("Id should not be specified");
        Book savedBook = bookRepo.save(book);
        log.info("Added book with new id {}", savedBook.getId());
        return savedBook;
    }

    @Override
    public void delete(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException(String.format("Book id - %d can not be <= 0", id));
        }
        bookRepo.findById(id)
                .map(book -> {
                    bookRepo.deleteById(book.getId());
                    log.info("Book with id {} deleted successfully.", id);
                    return 1; // ugly hack
                })
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    @Override
    public void delete(Book book) {
        bookRepo.deleteById(book.getId());
    }

}
