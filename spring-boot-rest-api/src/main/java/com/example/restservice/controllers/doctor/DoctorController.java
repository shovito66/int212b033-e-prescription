package com.example.restservice.controllers.doctor;


import com.example.restservice.models.doctor.Doctor;
import com.example.restservice.models.hospital.Hospital;
import com.example.restservice.repository.DoctorRepository;
import com.example.restservice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        try {
            Doctor _doctor = doctorRepository.save(
                    new Doctor(doctor.getHospitalID(),doctor.getDoctorName(),doctor.getSpeciality(),doctor.getAddress(),
                            doctor.getAbout(),doctor.getProfile_picture(),doctor.getCreatedOn()));
            return new ResponseEntity<>(_doctor, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam(required = false) String doctorName){
        try{
            List<Doctor> doctors = new ArrayList<Doctor>();
            if (doctorName==null)
                doctorRepository.findAll().forEach(doctors::add);
            else
                doctorRepository.findByDoctorNameContaining(doctorName).forEach(doctors::add);

            if (doctors.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctors/id/{id}")
    public ResponseEntity<Doctor> getHospitalById(@PathVariable("id") String id){
        Optional<Doctor> doctorData = doctorRepository.findById(id);
        if (doctorData.isPresent()){
            return new ResponseEntity<>(doctorData.get(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctors/name/{name}")
    public ResponseEntity<List<Doctor>>  getHospitalByName(@PathVariable("name") String name){
        try{
            List<Doctor> doctors = new ArrayList<Doctor>();
            doctorRepository.findByDoctorNameContaining(name).forEach(doctors::add);
            if (doctors.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/doctors/id/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") String id, @RequestBody Doctor doctor){
        Optional<Doctor> doctorData = doctorRepository.findById(id);

        if(doctorData.isPresent()){
            Doctor _doctor = doctorData.get();
            _doctor.setDoctorName(doctor.getDoctorName());
            _doctor.setAddress(doctor.getAddress());
            _doctor.setCreatedOn(doctor.getCreatedOn());
            _doctor.setHospitalID(doctor.getHospitalID());
            _doctor.setAbout(doctor.getAbout());
            _doctor.setSpeciality(doctor.getSpeciality());


            _doctor = doctorRepository.save(_doctor);
            return  new ResponseEntity<>(_doctor,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("id") String id){

        try {
            doctorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/doctors")
    public ResponseEntity<HttpStatus> deleteAllDoctors(){
        try {
            doctorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}