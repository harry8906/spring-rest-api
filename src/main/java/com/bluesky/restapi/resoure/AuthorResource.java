package com.bluesky.restapi.resoure;

import com.bluesky.restapi.entity.Author;
import com.bluesky.restapi.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/author")
public class AuthorResource {

    private AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping
    public Author addAuthor(@Valid @RequestBody Author author) {
        log.debug("Request to add book with title - {}", author.getName());
        return authorService.addAuthor(author);
    }

    @GetMapping("/{name}")
    public Author getAuthor(@PathVariable(name = "name") String name) {
        log.debug("Request to get book by name - {}", name);
        return authorService.getAuthorByName(name).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        log.debug("Request to delete book by id {}", id);
        authorService.delete(id);
    }


    @GetMapping("")
    public List<Author> getAllAuthors() {
        log.debug("Request to get all books");
        return authorService.getAllAuthors();
    }
}
