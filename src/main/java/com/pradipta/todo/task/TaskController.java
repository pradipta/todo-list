package com.pradipta.todo.task;

import com.pradipta.todo.App;
import com.pradipta.todo.dto.UserToTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping("")
    public String welcome() {
        return "Welcome";
    }

    @RequestMapping("/tasks")
    public List<Task> getAllTask(HttpServletRequest req) {
        String user = req.getUserPrincipal().getName();
        return service.getMyTask(user);
    }

    @RequestMapping("/tasks/pending")
    public List<Task> getPendingTask(HttpServletRequest req) {
        String user = req.getUserPrincipal().getName();
        return service.getPendingTask(user);
    }

    @RequestMapping("/tasks/completed")
    public List<Task> getCompletedTask(HttpServletRequest req) {
        String user = req.getUserPrincipal().getName();
        return service.getCompletedTask(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public Task addTask(@RequestBody Task task, HttpServletRequest req) {
        String user = req.getUserPrincipal().getName();
        return service.addTask(task, user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/markDone/{id}")
    public String markDone(@PathVariable String id, HttpServletRequest req) {
        String user = req.getUserPrincipal().getName();
        return service.markDone(Integer.parseInt(id), user);
    }

}
