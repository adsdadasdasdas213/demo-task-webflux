package com.example.demotask.controller;

import com.example.demotask.model.Movie;
import com.example.demotask.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public Flux<Movie> getALl() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Movie> getOne(@PathVariable Integer id) {
        return movieRepository.findById(id);
    }

    @PostMapping
    public Mono<Movie> add(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Mono<Movie> edit(@PathVariable Integer id, @RequestBody Movie movie) {
        return movieRepository.findById(id).map((c) -> {
            c.setName(movie.getName());
            c.setCountry(movie.getCountry());
            c.setDescription(movie.getDescription());
            return c;
        }).flatMap(c -> movieRepository.save(c));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return movieRepository.deleteById(id);
    }
}
