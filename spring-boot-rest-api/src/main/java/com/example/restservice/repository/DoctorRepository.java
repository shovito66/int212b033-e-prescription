package com.example.restservice.repository;

import com.example.restservice.models.doctor.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor,String> {
    List<Doctor> findByDoctorNameContaining(String docName);
}
