package com.pradipta.todo.task;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Task {
    @Id
    @Getter @Setter private String id;
    @Getter @Setter private String text;
    @Getter @Setter private Boolean isDone = Boolean.FALSE;

    public Task(String id, String text){
        this.id = id;
        this.text = text;
        this.isDone = Boolean.FALSE;
    }
}
