package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static ActorResponse toActorResponse(Actor actor){
        List<Movie> movies = actor.getMovies();
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(),
                actor.getBirthDate(), movies);
    }

    public static List<ActorResponse> toActorResponseList(List<Actor> actors){
        List<ActorResponse> actorResponses = new ArrayList<>();
        for(Actor actor : actors){
            actorResponses.add(toActorResponse(actor));
        }
        return actorResponses;
    }

    public static MovieResponse toMovieResponse(Movie movie){
        List<Actor> actors = movie.getActors();
        return new MovieResponse(movie.getId(), movie.getName(), movie.getReleaseDate(), actors);
    }

    public static List<MovieResponse> toMovieResponseList(List<Movie> movies){
        List<MovieResponse> movieResponses = new ArrayList<>();
        for(Movie movie : movies){
            movieResponses.add(toMovieResponse(movie));
        }
        return movieResponses;
    }
}
