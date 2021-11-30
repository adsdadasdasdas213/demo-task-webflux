package com.example.demotask.repository;

import com.example.demotask.model.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MovieRepository extends ReactiveCrudRepository<Movie, Integer> {

}
