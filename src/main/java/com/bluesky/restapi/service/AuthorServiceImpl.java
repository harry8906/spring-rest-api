package com.bluesky.restapi.service;

import com.bluesky.restapi.entity.Author;
import com.bluesky.restapi.entity.Book;
import com.bluesky.restapi.exception.ResourceNotFoundException;
import com.bluesky.restapi.repo.AuthorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        return authorRepo.findByFirstName(name);
    }

    @Override
    public Author update(Integer id, Author update) {
        log.info("Author update request for id {} ", id);
        return authorRepo.findById(id).map(author -> {
            author.setFirstName(update.getFirstName());
            author.setLastName(update.getLastName());
            Author savedAuthor = authorRepo.save(author);
            log.info("Book update request - successful");
            return savedAuthor;
        }).orElseGet(() -> {
            update.setId(id);
            authorRepo.save(update);
            log.info("Book update request - record not found with id {}, added new entry", id);
            return update;
        });
    }

    @Override
    public Author addAuthor(Author author) {
        //if (author.getId() != null) throw new IllegalArgumentException("Id should not be specified");
        Author savedAuthor = authorRepo.save(author);
        log.info("Added author with new id {}", savedAuthor.getId());
        return savedAuthor;
    }

    @Override
    public void delete(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException(String.format("Author id - %d can not be <= 0", id));
        }
        authorRepo.findById(id)
                .map(book -> {
                    authorRepo.deleteById(book.getId());
                    log.info("Book with id {} deleted successfully.", id);
                    return 1; // ugly hack
                })
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    @Override
    public void delete(Author author) {
        authorRepo.deleteById(author.getId());
    }

}
