package org.example.jdbccap15.mapper;

import org.example.jdbccap15.model.Task;
import org.example.jdbccap15.service.io.GetTasksByAssigneeId;

public class MapTasksToFindTasksByPersonOut {

    public static GetTasksByAssigneeId.TaskOut map(Task task) {
        return new GetTasksByAssigneeId.TaskOut(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignee().getFullName(),
                task.getReporter().getFullName(),
                task.getStatus().name()
        );
    }
}
