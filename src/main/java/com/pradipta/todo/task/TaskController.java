package com.pradipta.todo.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradipta.todo.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService service;

    @RequestMapping("/tasks")
    public List<Task> getAllTask() {
        return service.getAllTask();
    }

    @RequestMapping("/tasks/pending")
    public List<Task> getPendingTask() {
        return service.getPendingTask();
    }

    @RequestMapping("/tasks/completed")
    public List<Task> getCompletedTask() {
        return service.getCompletedTask();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        ObjectMapper map = new ObjectMapper();
        String json = "";
        try {
            json = map.writeValueAsString(service.addTask(task));
        }catch (JsonProcessingException e) {
            return Util.createResponseEntity("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Util.createResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/markDone/{id}")
    public ResponseEntity<?> markDone(@PathVariable String id) {
        return new ResponseEntity<>(service.markDone(id), HttpStatus.OK);
    }
}
