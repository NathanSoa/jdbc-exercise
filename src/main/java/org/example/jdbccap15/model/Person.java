package org.example.jdbccap15.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
