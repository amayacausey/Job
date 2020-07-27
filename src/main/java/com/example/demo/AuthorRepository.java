package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AuthorRepository extends CrudRepository<Author,Long> {
    ArrayList<Author> findAllByIdGreaterThanOrderByName(long indexNum);
}
