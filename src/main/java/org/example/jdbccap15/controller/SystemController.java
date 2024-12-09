package org.example.jdbccap15.controller;


import org.example.jdbccap15.controller.io.SavePersonRequest;
import org.example.jdbccap15.controller.io.SaveTaskRequest;
import org.example.jdbccap15.controller.io.UpdateTaskStatusRequest;
import org.example.jdbccap15.model.Person;
import org.example.jdbccap15.model.Task;
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

    @PostMapping("/person")
    public ResponseEntity<Void> saveNewPerson(@RequestBody SavePersonRequest request) {
        systemService.saveNewPerson(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<Set<Task>> findAllTaskStatuses() {
        Set<Task> tasks = systemService.findAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = systemService.getTaskById(taskId);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
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

    @GetMapping("/person")
    public ResponseEntity<Set<Person>> findAllPeople() {
        Set<Person> people = systemService.findAllPeople();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/assignee/tasks/{taskId}")
    public ResponseEntity<Person> getAssigneeByTaskId(@PathVariable Long taskId) {
        Person assignee = systemService.getAssigneeByTaskId(taskId);
        return assignee != null ? ResponseEntity.ok(assignee) : ResponseEntity.notFound().build();
    }

    @PostMapping("/tasks")
    public ResponseEntity<Void> saveNewTask(@RequestBody SaveTaskRequest request) {
        systemService.saveNewTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/tasks/{taskId}/status")
    public ResponseEntity<Void> updateTaskStatus(@PathVariable Long taskId, @RequestBody UpdateTaskStatusRequest request) {
        systemService.updateTaskStatus(taskId, TaskStatus.valueOf(request.newStatus()));
        return ResponseEntity.ok().build();
    }
}