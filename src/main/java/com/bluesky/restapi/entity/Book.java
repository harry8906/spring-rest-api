package com.bluesky.restapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Null;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "This id field should be null")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("author")
    private String author;
}
