package com.example.revenueservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class Actor implements Serializable {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("actorType")
    private ActorTypes actorType;

    @JsonProperty("dateOfBirth")
    private Timestamp dateOfBirth;

    @JsonProperty("salary")
    private int salary;

    @JsonProperty("fees")
    private int fees;

    @JsonProperty("createdAt")
    private Timestamp createdAt;

    @JsonProperty("updatedAt")
    private Timestamp updatedAt;
}
