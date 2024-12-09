package org.example.jdbccap15.controller.io;

public record SavePersonRequest(String firstName, String lastName, String email, String birthdate) {
}
