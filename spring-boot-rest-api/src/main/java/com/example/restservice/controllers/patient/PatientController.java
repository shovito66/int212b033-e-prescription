package com.example.restservice.controllers.patient;

import com.example.restservice.models.doctor.Doctor;
import com.example.restservice.models.patient.Patient;
import com.example.restservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        try {
            Patient _patient = patientRepository.save(
                    new Patient(
                            patient.getHospitalId(), patient.getDoctorId(), patient.getRegDateTime(), patient.getDOB(), patient.getAge(),
                            patient.getGender(), patient.getOccupation(), patient.getHealth_insurance_no(), patient.getHealthCareProvider(),
                            patient.getAddress(), patient.getContact_no(), patient.getCreatedOn()
                    ));
            return new ResponseEntity<>(_patient, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients(@RequestParam(required = false) String patientName){
        try{
            List<Patient> patients = new ArrayList<Patient>();
            if (patientName==null)
                patientRepository.findAll().forEach(patients::add);
            else
                patientRepository.findByPatientNameContaining(patientName).forEach(patients::add);

            if (patients.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patients,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patients/id/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") String id){
        Optional<Patient> patientData = patientRepository.findById(id);
        if (patientData.isPresent()){
            return new ResponseEntity<>(patientData.get(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patients/name/{name}")
    public ResponseEntity<List<Patient>>  getPatientByName(@PathVariable("name") String name){
        try{
            List<Patient> patients = new ArrayList<Patient>();
            patientRepository.findByPatientNameContaining(name).forEach(patients::add);
            if (patients.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patients,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") String id){

        try {
            patientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/patients")
    public ResponseEntity<HttpStatus> deleteAllPatients(){
        try {
            patientRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/patients/id/{id}")
    public ResponseEntity<Patient> updateDoctor(@PathVariable("id") String id, @RequestBody Patient patient){
        Optional<Patient> patientData = patientRepository.findById(id);

        if(patientData.isPresent()){
            Patient _patient = patientData.get();
            _patient.setHospitalId(patient.getHospitalId());
            _patient.setDoctorId(patient.getDoctorId());
            _patient.setRegDateTime(patient.getRegDateTime());
            _patient.setCreatedOn(patient.getCreatedOn());
            _patient.setAddress(patient.getAddress());
            _patient.setAge(patient.getAge());
            _patient.setContact_no(patient.getContact_no());
            _patient.setDOB(patient.getDOB());
            _patient.setGender(patient.getGender());
            _patient.setOccupation(patient.getOccupation());
            _patient.setHealth_insurance_no(patient.getHealth_insurance_no());
            _patient.setHealthCareProvider(patient.getHealthCareProvider());

            _patient = patientRepository.save(_patient);
            return  new ResponseEntity<>(_patient,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
