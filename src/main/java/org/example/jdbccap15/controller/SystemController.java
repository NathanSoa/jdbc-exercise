package org.example.jdbccap15.controller;


import org.example.jdbccap15.controller.io.SaveTaskRequest;
import org.example.jdbccap15.model.Person;
import org.example.jdbccap15.model.TaskStatus;
import org.example.jdbccap15.service.SystemService;
import org.example.jdbccap15.service.io.GetTasksByAssigneeId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class SystemController {
    private final SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }


    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = systemService.getPersonById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tasks/assignee/{id}")
    public ResponseEntity<GetTasksByAssigneeId> getTasksByAssigneeId(@PathVariable Long id) {
        GetTasksByAssigneeId tasks = systemService.getTasksByAssigneeId(id);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/people")
    public ResponseEntity<Set<Person>> findAllPeople() {
        Set<Person> people = systemService.findAllPeople();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/task/assignee/{taskId}")
    public ResponseEntity<Person> getAssigneeByTaskId(@PathVariable Long taskId) {
        Person assignee = systemService.getAssigneeByTaskId(taskId);
        return assignee != null ? ResponseEntity.ok(assignee) : ResponseEntity.notFound().build();
    }

    @PostMapping("/task")
    public ResponseEntity<Void> saveNewTask(@RequestBody SaveTaskRequest request) {
        systemService.saveNewTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/task/{taskId}/status")
    public ResponseEntity<Void> updateTaskStatus(@PathVariable Long taskId, @RequestParam TaskStatus newStatus) {
        systemService.updateTaskStatus(taskId, newStatus);
        return ResponseEntity.ok().build();
    }
}