package com.example.demotask.controller;

import com.example.demotask.model.Movie;
import com.example.demotask.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    // most popular SLF4j

    // logger.trace("A TRACE Message");
    // logger.debug("A DEBUG Message");
    // logger.info("An INFO Message");
    // logger.warn("A WARN Message");
    // logger.error("An ERROR Message");

    Logger logger = LoggerFactory.getLogger(MovieController.class);

    final
    MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public Flux<Movie> getALl() {
        logger.info("INFO Find all");
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Movie> getOne(@PathVariable Integer id) {
        logger.info("INFO get One");
        return movieRepository.findById(id);
    }

    @PostMapping
    public Mono<Movie> add(@RequestBody Movie movie) {
        logger.info("INFO Add");
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Mono<Movie> edit(@PathVariable Integer id, @RequestBody Movie movie) {
        logger.info("INFO Edit");
        return movieRepository.findById(id).map((c) -> {
            c.setName(movie.getName());
            c.setCountry(movie.getCountry());
            c.setDescription(movie.getDescription());
            return c;
        }).flatMap(movieRepository::save);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        logger.warn("Warn Delete");
        return movieRepository.deleteById(id);
    }
}
