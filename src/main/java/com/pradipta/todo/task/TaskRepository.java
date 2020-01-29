package com.pradipta.todo.task;

import com.pradipta.todo.dto.UserToTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    //GetTAsks
    //GetPendingTasks
    //GetCompletedTasks
    //AddTask
    //Delete Task
    //GetTaskByUserId

    @Transactional
    @Modifying
    @Query("Update Task set user_task_fk = :user where id= :id")
    void updateTask(@Param("id") int id, @Param("user")String user);

    @Query(value = "select user_task_fk from Task where id= :id", nativeQuery = true)
    String getUser(@Param("id") int id);
}
