package com.project.patientmanagement.service;

import com.project.patientmanagement.Mapper.PatientMapper;
import com.project.patientmanagement.dto.PatientRequestDTO;
import com.project.patientmanagement.dto.PatientResponseDTO;
import com.project.patientmanagement.exception.EmailAlreadexistsException;
import com.project.patientmanagement.exception.PatientNotFoundException;
import com.project.patientmanagement.model.Patient;
import com.project.patientmanagement.repository.PatientRepository;
import com.sun.java.accessibility.util.GUIInitializedListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    public PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
        throw new EmailAlreadexistsException("Email already exists" + patientRequestDTO.getEmail());
        }
         Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
         return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO , UUID Id) {
        Patient patient = patientRepository.findById(Id).orElseThrow(()->new PatientNotFoundException("Patient not found" + Id));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), Id)) {
            throw new EmailAlreadexistsException("Email already exists" + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
// registered date should not be updated
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID Id) {
        patientRepository.deleteById(Id);
    }
}
