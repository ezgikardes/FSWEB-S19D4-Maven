package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    List<ActorResponse> findAll(){
        List<Actor> actors = actorService.findAll();
        return Converter.toActorResponseList(actors);
    }

    @GetMapping("/{id}")
    ActorResponse findById(@PathVariable Long id){
        Actor foundActor = actorService.findById(id);
        return Converter.toActorResponse(foundActor);
    }

    @PostMapping
    ActorResponse save(@RequestBody ActorRequest actorRequest){
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();

        for(Movie movie : movies){
            actor.addMovie(movie);
            movie.addActor(actor);
        }
        actorService.save(actor);
        return Converter.toActorResponse(actor);
    }


    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable Long id, @RequestBody ActorRequest actorRequest) {
        if (actorRequest == null || actorRequest.getActor() == null) {
            throw new ApiException("Actor or ActorRequest cannot be null", HttpStatus.BAD_REQUEST);
        }

        // Güncellemeye devam et
        actorService.findById(id); // Bu zaten null kontrolü yapıyor ve hata fırlatıyor.
        Actor actorToUpdate = actorRequest.getActor();
        actorToUpdate.setId(id);
        actorToUpdate.setMovies(actorRequest.getMovies());
        actorService.save(actorToUpdate);

        return Converter.toActorResponse(actorToUpdate);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
    }

}
