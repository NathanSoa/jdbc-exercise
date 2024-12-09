package org.example.jdbccap15.repository;

import org.example.jdbccap15.model.Person;
import org.example.jdbccap15.model.Task;
import org.example.jdbccap15.model.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SystemRepository {
    Person getPersonById(Long id);
    Set<Task> getTasksByAssigneeId(Long id);
    Set<Person> findAllPeople();
    Person getAssigneeByTaskId(Long taskId);
    void savePerson(Person person);
    void saveNewTask(Task task, Long assigneeId, Long reporterId);
    void updateTaskStatus(Long taskId, TaskStatus newStatus);
}
