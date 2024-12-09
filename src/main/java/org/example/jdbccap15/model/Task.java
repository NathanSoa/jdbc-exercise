package org.example.jdbccap15.model;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private Person assignee;
    private Person reporter;
    private TaskStatus status;
}

