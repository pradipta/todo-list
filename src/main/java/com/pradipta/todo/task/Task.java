package com.pradipta.todo.task;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Boolean isDone = Boolean.FALSE;

    public Task(int id, String text){
        this.id = (long)id;
        this.text = text;
        this.isDone = Boolean.FALSE;
    }
}
