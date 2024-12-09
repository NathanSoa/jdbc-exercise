package org.example.jdbccap15.mapper;

import org.example.jdbccap15.controller.io.SavePersonRequest;
import org.example.jdbccap15.model.Person;

import java.time.LocalDate;

public class MapSavePersonRequestToPerson {
    public static Person map(SavePersonRequest request) {
        Person person = new Person();
        person.setFirstName(request.firstName());
        person.setLastName(request.lastName());
        person.setEmail(request.email());
        person.setBirthdate(LocalDate.parse(request.birthdate()));
        return person;
    }
}
