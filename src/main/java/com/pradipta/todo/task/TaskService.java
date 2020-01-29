package com.pradipta.todo.task;

import com.pradipta.todo.dto.UserToTask;
import com.pradipta.todo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repo;
    @Autowired
    private UserRepository userRepository;

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

    public Task markDone(int id) {
        //repo.findById(id).get().setIsDone(true);
        //TODO: figure out update in one line lambda
        Task task = repo.findById(id).get();    //TODO: handle empty optional
        task.setIsDone(true);
        return repo.save(task);
    }

    public void updateTask(int id, Task task, String user) {
        repo.updateTask(id, user);
        userRepository.findByUsername(user).get().getTasks().add(task);
    }
}
