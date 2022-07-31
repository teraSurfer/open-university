package com.university.users.dto;

import com.university.users.entities.ActorTypes;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class ActorDTO {
    @NotNull
    @NotEmpty
    private String name;

    @NotEmpty
    @NotNull
    private String actorType;

    @NotNull
    private long dateOfBirth;

    private int salary;

    private int fees;
}
