package com.example.revenueservice.dto;

public enum ActorTypes {
    STUDENT("STUDENT"),
    FACULTY("FACULTY");

    public final String actorType;

    ActorTypes(String actorType) {
        this.actorType = actorType;
    }
}
