package com.university.users.entities;

public enum ActorTypes {
    STUDENT("STUDENT"),
    FACULTY("FACULTY");

    public final String actorType;

    ActorTypes(String actorType) {
        this.actorType = actorType;
    }
}
