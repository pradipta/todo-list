package com.pradipta.todo.user;

import com.pradipta.todo.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    private String username;
    private String password;
    private Boolean active;
    private String roles;
    @OneToMany(targetEntity = Task.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_task_fk", referencedColumnName = "username")
    private List<Task> tasks;
}
