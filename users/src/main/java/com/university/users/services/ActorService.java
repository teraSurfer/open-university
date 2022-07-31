package com.university.users.services;

import com.university.users.entities.Actor;
import com.university.users.persistence.ActorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public void setActorRepository(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActors() {
        List<Actor> actors = null;
        try {
            actors = this.actorRepository.findAll();
        } catch(Exception e) {
            log.error("Could not fetch actors - {}", e.getMessage());
        }

        return actors;
    }

    public Actor getActorById(long id) {
        try {
            Optional<Actor> optionalActor = actorRepository.findById(id);
            return optionalActor.orElseThrow(() -> new RuntimeException("No such actor."));
        } catch(Exception e) {
            log.error("Could not fetch actor - {}", e.getMessage());
        }
        return null;
    }

    public Actor createActor(Actor actor) {
        Actor newActor = null;
        try {
            newActor = this.actorRepository.save(actor);
        } catch (Exception e) {
            log.error("Could not create actor - {}", e.getMessage());
        }
        return newActor;
    }

    public Actor updateActor(long id, Actor actor) {
        try {
            Optional<Actor> optionalActor = actorRepository.findById(id);
            Actor actorInDb = optionalActor.orElseThrow(() -> new RuntimeException("No such actor."));
            actorInDb.setName(actor.getName());
            actorInDb.setDateOfBirth(actor.getDateOfBirth());
            actorInDb.setActorType(actor.getActorType());
            actorInDb.setFees(actor.getFees());
            actorInDb.setSalary(actor.getSalary());
            actorRepository.save(actorInDb);
            return actorInDb;
        } catch (Exception e) {
            log.error("Could not create actor - {}", e.getMessage());
        }
        return null;
    }

    public Actor deleteActor(long id) {
        Actor actor = null;
        try {
            Optional<Actor> deletedActor = actorRepository.findById(id);
            if(deletedActor.isPresent()) {
                actor = deletedActor.get();
                actorRepository.delete(actor);
            } else {
               throw new RuntimeException("No such user found");
            }
        } catch (Exception e) {
            log.error("Could not delete actor - {}", e.getMessage());
        }
        return actor;
    }
}
