package com.bluesky.restapi.resoure;

import com.bluesky.restapi.entity.Book;
import com.bluesky.restapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/{name}")
    public Book getBook(@PathVariable(name = "name") String name) {
        return bookService.getBookByName(name).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        bookService.delete(id);
    }

    @RequestMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable("author") String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
