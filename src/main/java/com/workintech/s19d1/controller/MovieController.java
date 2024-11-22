package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.repository.MovieRepository;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieRepository movieRepository;
    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<MovieResponse> findAll(){
        List<Movie> movies = movieService.findAll();
        return Converter.toMovieResponseList(movies);
    }


    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable Long id){
        Movie movie = movieService.findById(id);
        return Converter.toMovieResponse(movie);
    }

    @PostMapping
    public MovieResponse save(@RequestBody MovieRequest movieRequest){
        List<Actor> actors = movieRequest.getActors();
        Movie movie = movieRequest.getMovie();

        for(Actor actor : actors){
            movie.addActor(actor);
        }
        movieService.save(movie);

        return Converter.toMovieResponse(movie);
    }

    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable Long id, @RequestBody MovieRequest movieRequest){
        movieService.findById(id); //null check için

        Movie movieToUpdate = movieRequest.getMovie();
        movieToUpdate.setId(id);

        movieRepository.save(movieToUpdate);

        return Converter.toMovieResponse(movieToUpdate);
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable long id){
        Movie movie = movieService.findById(id);
        movieRepository.delete(movie);
        return movie;
    }
}