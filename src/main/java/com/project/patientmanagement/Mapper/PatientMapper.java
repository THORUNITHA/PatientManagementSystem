package com.project.patientmanagement.Mapper;

import com.project.patientmanagement.dto.PatientRequestDTO;
import com.project.patientmanagement.dto.PatientResponseDTO;
import com.project.patientmanagement.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO patient) {
        Patient createPatient = new Patient();
        createPatient.setName(patient.getName());
        createPatient.setAddress(patient.getAddress());
        createPatient.setEmail(patient.getEmail());
        createPatient.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));
        createPatient.setRegisteredDate(LocalDate.parse(patient.getRegisteredDate()));
        return createPatient;
    }
}