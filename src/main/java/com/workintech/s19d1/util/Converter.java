package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Converter {

    public static ActorResponse toActorResponse(Actor actor){
        List<Movie> movies = actor.getMovies() != null ? actor.getMovies() : Collections.emptyList();
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(),
                actor.getBirthDate(), movies);
    }

    public static List<ActorResponse> toActorResponseList(List<Actor> actors){
        if(actors == null){
            return Collections.emptyList();
        }

        List<ActorResponse> actorResponses = new ArrayList<>();

        for(Actor actor : actors){
            actorResponses.add(toActorResponse(actor));
        }
        return actorResponses;
    }



}
