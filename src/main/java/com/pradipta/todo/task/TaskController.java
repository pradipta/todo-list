package com.pradipta.todo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService service;

    @RequestMapping("/tasks")
    public List<Task> getAllTask(){
        return service.getAllTask();
    }

    @RequestMapping("/tasks/pending")
    public List<Task> getPendingTask(){
        return service.getPendingTask();
    }

    @RequestMapping("/tasks/completed")
    public List<Task> getCompletedTask(){
        return service.getCompletedTask();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public Task addTask(@RequestBody Task task){
        return service.addTask(task);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/markDone/{id}")
    public Task markDone(@PathVariable String id){
        return service.markDone(id);
    }
}
