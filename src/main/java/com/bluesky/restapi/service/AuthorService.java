package com.bluesky.restapi.service;

import com.bluesky.restapi.entity.Author;
import com.bluesky.restapi.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorByName(String name);

    Author update(Integer id, Author update);

    Author addAuthor(Author author);

    void delete(Integer id);

    void delete(Author author);
}
