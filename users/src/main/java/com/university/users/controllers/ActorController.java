package com.university.users.controllers;

import com.university.users.dto.ActorDTO;
import com.university.users.entities.Actor;
import com.university.users.services.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    @GetMapping("")
    public ResponseEntity<List<Actor>> getActors() {
        log.info("request received");
        List<Actor> actors = actorService.getActors();
        if (actors == null) {
            return new ResponseEntity<>(actors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") long id) {
        log.info("request received");
        Actor actor = actorService.getActorById(id);
        if (actor == null) {
            return new ResponseEntity<>(actor, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Actor> createActor(@RequestBody @Valid ActorDTO actorDTO) {
        log.info("request body - {}, {}, {}", actorDTO.getName(), actorDTO.getDateOfBirth(), actorDTO.getActorType());
        Actor createdActor = actorService.createActor(new Actor(actorDTO));
        return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> deleteActor(@PathVariable("id") long id) {
        Actor deletedActor = actorService.deleteActor(id);
        return new ResponseEntity<>(deletedActor, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") long id, @RequestBody @Valid ActorDTO actorDTO) {
        Actor updatedActor = actorService.updateActor(id, new Actor(actorDTO));
        return new ResponseEntity<>(updatedActor, HttpStatus.ACCEPTED);
    }

}
