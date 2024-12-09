package org.example.jdbccap15.model;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Person assignee;
    private Person reporter;
    private TaskStatus status;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public Person getReporter() {
        return reporter;
    }

    public void setReporter(Person reporter) {
        this.reporter = reporter;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

