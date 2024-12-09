package org.example.jdbccap15.controller.io;

public record SaveTaskRequest(
        String title,
        String description,
        Long assigneeId,
        Long reporterId
) {
}
