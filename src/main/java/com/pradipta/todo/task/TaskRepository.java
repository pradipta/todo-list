package com.pradipta.todo.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    //GetTAsks
    //GetPendingTasks
    //GetCompletedTasks
    //AddTask
    //Delete Task
}
