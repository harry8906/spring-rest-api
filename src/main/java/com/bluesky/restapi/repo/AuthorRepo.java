package com.bluesky.restapi.repo;

import com.bluesky.restapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Optional<Author> findByFirstName(String name);
}
