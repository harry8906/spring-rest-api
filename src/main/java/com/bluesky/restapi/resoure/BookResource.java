package com.bluesky.restapi.resoure;

import com.bluesky.restapi.entity.Book;
import com.bluesky.restapi.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/book")
public class BookResource {

    private BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        log.debug("Request to add book with title - {}", book.getName());
        return bookService.addBook(book);
    }

    @GetMapping("/{name}")
    public Book getBook(@PathVariable(name = "name") String name) {
        log.debug("Request to get book by name - {}", name);
        return bookService.getBookByName(name).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        log.debug("Request to delete book by id {}", id);
        bookService.delete(id);
    }

    @RequestMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable("author") String author) {
        log.debug("Request to get book by author - {}", author);
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        log.debug("Request to get all books");
        return bookService.getAllBooks();
    }
}
