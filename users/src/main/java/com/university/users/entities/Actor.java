package com.university.users.entities;

import com.university.users.dto.ActorDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor implements Serializable {

    private static final long serialVersionUID = 1932191611022741079L;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column(name="actor_type")
    private ActorTypes actorType;

    @Column(name="date_of_birth")
    private Timestamp dateOfBirth;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    @PrePersist
    void beforeSave() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(this.createdAt == null) {
            this.createdAt = now;
        }
        this.updatedAt = now;
    }

    public Actor() {}

    public Actor(ActorDTO actorDTO) {
        this.name = actorDTO.getName();
        this.actorType = ActorTypes.valueOf(actorDTO.getActorType());
        this.dateOfBirth = new Timestamp(actorDTO.getDateOfBirth());
    }

}
