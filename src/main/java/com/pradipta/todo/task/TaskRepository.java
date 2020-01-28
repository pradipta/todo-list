package com.pradipta.todo.task;

import com.pradipta.todo.dto.UserToTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    //GetTAsks
    //GetPendingTasks
    //GetCompletedTasks
    //AddTask
    //Delete Task
/*   @Query("SELECT new com.pradipta./t/odo.dto.UserToTask(u.id, t.id) from User u JOIN u.tasks t")*/
//    public List<UserToTask> getMap();
    //GetTaskByUserId

    @Query("Select t.id, t.text,t.isDone,u.id,u.username from User u JOIN u.tasks t where u.id = :id")
    public List<Task> getTasksForUser(@Param("id") int id);

}
