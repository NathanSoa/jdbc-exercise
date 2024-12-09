package org.example.jdbccap15.service;

import lombok.RequiredArgsConstructor;
import org.example.jdbccap15.controller.io.SavePersonRequest;
import org.example.jdbccap15.controller.io.SaveTaskRequest;
import org.example.jdbccap15.mapper.MapSavePersonRequestToPerson;
import org.example.jdbccap15.mapper.MapTasksToFindTasksByPersonOut;
import org.example.jdbccap15.model.Person;

import org.example.jdbccap15.model.Task;
import org.example.jdbccap15.model.TaskStatus;
import org.example.jdbccap15.repository.SystemRepository;
import org.example.jdbccap15.service.io.GetTasksByAssigneeId;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SystemService {
    private final SystemRepository systemRepository;

    public SystemService(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    public void updateTaskStatus(Long taskId, TaskStatus newStatus) {
        systemRepository.updateTaskStatus(taskId, newStatus);
    }

    public void saveNewPerson(SavePersonRequest request) {
        systemRepository.savePerson(MapSavePersonRequestToPerson.map(request));
    }

    public void saveNewTask(SaveTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(TaskStatus.OPEN);
        systemRepository.saveNewTask(task, request.assigneeId(), request.reporterId());
    }

    public GetTasksByAssigneeId getTasksByAssigneeId(Long id) {
        return new GetTasksByAssigneeId(
                this.systemRepository
                        .getTasksByAssigneeId(id)
                        .stream()
                        .map(MapTasksToFindTasksByPersonOut::map)
                        .collect(Collectors.toSet())
        );
    }

    public Person getPersonById(Long id) {
        return systemRepository.getPersonById(id);
    }

    public Person getAssigneeByTaskId(Long taskId) {
        return systemRepository.getAssigneeByTaskId(taskId);
    }

    public Set<Person> findAllPeople() {
        return systemRepository.findAllPeople();
    }
}
