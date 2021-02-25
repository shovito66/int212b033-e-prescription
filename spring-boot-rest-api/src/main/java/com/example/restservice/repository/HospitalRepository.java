package com.example.restservice.repository;

import com.example.restservice.models.hospital.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HospitalRepository extends MongoRepository<Hospital,String> {
    List<Hospital> findByHospitalNameContaining(String hospitalName);
}
