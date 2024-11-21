package com.workintech.s19d1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class ActorRequest {

    @JsonProperty("movies") // JSON içinde "movies" alanına eşlenir
    private List<Movie> movies;

    @JsonProperty("actor") // JSON içinde "actor" alanına eşlenir
    private Actor actor;
}
