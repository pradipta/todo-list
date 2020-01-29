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

    public List<Task> getPendingTask(String username){
        return userRepository.findByUsername(username).get().getTasks().stream().filter(x -> Boolean.FALSE.equals(x.getIsDone())).collect(Collectors.toList());
    }

    public List<Task> getMyTask(String username){
        return userRepository.findByUsername(username).get().getTasks();
    }

    public List<Task> getCompletedTask(String username){
        return userRepository.findByUsername(username).get().getTasks().stream().filter(x -> Boolean.TRUE.equals(x.getIsDone())).collect(Collectors.toList());
    }

    public Task addTask(Task task, String username){
        Task savedTask = repo.save(task);
        int id = savedTask.getId();
        updateTask(id, username);
        return savedTask;
    }

    public String markDone(int id, String username) {
        //repo.findById(id).get().setIsDone(true);
        //TODO: figure out update in one line lambda
        Task task = repo.findById(id).get();    //TODO: handle empty optional
        if (!repo.getUser(id).equalsIgnoreCase(username)){
            return "Failed";
        }
        task.setIsDone(true);
        repo.save(task);
        return "Success";
    }

    private void updateTask(int id, String user) {
        repo.updateTask(id, user);
        //userRepository.findByUsername(user).get().getTasks().add(task);
    }
}
