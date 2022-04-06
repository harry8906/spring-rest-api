package com.bluesky.restapi;

import com.bluesky.restapi.entity.Author;
import com.bluesky.restapi.entity.Book;
import com.bluesky.restapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringRestApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringRestApiApplication.class, args);

        Author apjKalam = Author.builder()
                .firstName("APJ").lastName("Kalam").build();

        Book wingsOfFire = Book.builder()
                .name("Wings of Fire").author(apjKalam).build();

        Book mission202 = Book.builder()
                .name("India 2020").author(apjKalam).build();

        apjKalam.setBooks(Arrays.asList(wingsOfFire, mission202));

        context.getBean(AuthorService.class).addAuthor(apjKalam);
    }

}
