package com.bluesky.restapi.service;

import com.bluesky.restapi.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookByName(String name);

    List<Book> getBooksByAuthor(String author);

    Book update(Integer id, Book update);

    Book addBook(Book book);

    void delete(Integer id);

    void delete(Book book);
}
