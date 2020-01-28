package com.pradipta.todo.task;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
    private Boolean isDone = Boolean.FALSE;
    @CreationTimestamp
    private LocalDateTime creationDate;

    public Task(int id, String text, Date date){
        this.id = id;
        this.text = text;
        this.isDone = Boolean.FALSE;
    }
}
