package com.pradipta.todo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repo;

    //TODO handle exceptions

    public List<Task> getAllTask(){
        return repo.findAll();
    }

    public List<Task> getPendingTask(){
        return repo.findAll().stream().filter(x -> Boolean.FALSE.equals(x.getIsDone())).collect(Collectors.toList());
    }

    public List<Task> getCompletedTask(){
        return repo.findAll().stream().filter(x -> Boolean.TRUE.equals(x.getIsDone())).collect(Collectors.toList());
    }

    public Task addTask(Task task){
        return repo.save(task);
    }

    public Task markDone(String id) {
        //repo.findById(id).get().setIsDone(true);
        //TODO: figure out update in one line lambda
        Task task = repo.findById(id).get();    //TODO: handle empty optional
        task.setIsDone(true);
        return repo.save(task);
    }
}
