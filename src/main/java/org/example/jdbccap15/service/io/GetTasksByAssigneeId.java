package org.example.jdbccap15.service.io;

import java.util.Set;

public record GetTasksByAssigneeId(
        Set<TaskOut> tasks
) {
    public record TaskOut(
            Long taskId,
            String title,
            String description,
            String assigneeName,
            String reporterName,
            String status
    ) {}
}
