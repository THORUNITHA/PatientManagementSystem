package com.project.patientmanagement.exception;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(String message) {
        super(message);
    }
}
