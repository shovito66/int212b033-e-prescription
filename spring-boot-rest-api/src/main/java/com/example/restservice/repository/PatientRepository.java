package com.example.restservice.repository;

import com.example.restservice.models.hospital.Hospital;
import com.example.restservice.models.patient.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient,String> {
    List<Patient> findByPatientNameContaining(String patientName);
}
