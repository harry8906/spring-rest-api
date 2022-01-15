package com.bluesky.restapi.repo;

import com.bluesky.restapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    Optional<Book> findByName(String name);
    List<Book> findAllByAuthor(String author);
}
