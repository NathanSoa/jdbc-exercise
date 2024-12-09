package org.example.jdbccap15.repository;

import org.example.jdbccap15.model.Person;
import org.example.jdbccap15.model.Task;
import org.example.jdbccap15.model.TaskStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class SystemRepositoryImp implements SystemRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection connection;

    @PostConstruct
    public void init() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person getPersonById(Long id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPerson(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Task> getTasksByAssigneeId(Long id) {
        String sql = "SELECT * FROM task WHERE assignee_id = ?";
        Set<Task> tasks = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tasks.add(mapTask(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Set<Person> findAllPeople() {
        String sql = "SELECT * FROM person";
        Set<Person> people = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                people.add(mapPerson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Person getAssigneeByTaskId(Long taskId) {
        String sql = "SELECT p.* FROM person p JOIN task t ON p.id = t.assignee_id WHERE t.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPerson(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void savePerson(Person person) {
        String sql = "INSERT INTO person (first_name, last_name, email, birthdate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getEmail());
            ps.setDate(4, Date.valueOf(person.getBirthdate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNewTask(Task task, Long assigneeId, Long reporterId) {
        String sql = "INSERT INTO task (title, description, status, assignee_id, reporter_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus().name());
            ps.setLong(4, assigneeId);
            ps.setLong(5, reporterId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTaskStatus(Long taskId, TaskStatus newStatus) {
        String sql = "UPDATE task SET status = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus.name());
            ps.setLong(2, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Person mapPerson(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setEmail(rs.getString("email"));
        return person;
    }

    private Task mapTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setStatus(TaskStatus.valueOf(rs.getString("status")));
        return task;
    }
}