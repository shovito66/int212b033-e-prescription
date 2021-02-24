package com.example.restservice.controllers.hospital;

import com.example.restservice.models.hospital.Hospital;
import com.example.restservice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class HospitalController {

    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping("/hospitals")
    public ResponseEntity<List<Hospital>>  getAllHospitals(@RequestParam(required = false) String hospitalName){
        try {
            List<Hospital> hospitals = new ArrayList<Hospital>();

            if (hospitalName == null)
                hospitalRepository.findAll().forEach(hospitals::add);
            else
                hospitalRepository.findByHospitalNameContaining(hospitalName).forEach(hospitals::add);

            if (hospitals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hospitals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hospitals/id/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable("id") String id){
        Optional<Hospital> hospitalData = hospitalRepository.findById(id);
        if (hospitalData.isPresent()){
            return new ResponseEntity<>(hospitalData.get(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hospitals/name/{name}")
    public ResponseEntity<List<Hospital>>  getHospitalByName(@PathVariable("name") String name){
        try{
            List<Hospital> hospitals = new ArrayList<Hospital>();
            hospitalRepository.findByHospitalNameContaining(name).forEach(hospitals::add);
            if (hospitals.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(hospitals,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/hospitals")
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        try {
            Hospital _hospital = hospitalRepository.save(new Hospital(hospital.getHospitalName(), hospital.getBranchName(),
                        hospital.getAddress(), hospital.getEmail(), hospital.getContact(), hospital.getCreatedOn()));
            return new ResponseEntity<>(_hospital, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> updateTutorial(@PathVariable("id") String id, @RequestBody Hospital hospital) {
        Optional<Hospital> hospitalData = hospitalRepository.findById(id);

        if (hospitalData.isPresent()) {
            Hospital _hospital = hospitalData.get();
            _hospital.setHospitalName(hospital.getHospitalName());
            _hospital.setAddress(hospital.getAddress());
            _hospital.setContact(hospital.getContact());
            _hospital.setBranchName(hospital.getBranchName());
            _hospital.setEmail(hospital.getEmail());
            _hospital.setCreatedOn(hospital.getCreatedOn());

            _hospital = hospitalRepository.save(_hospital);
            return new ResponseEntity<>(_hospital, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/hospitals/{id}")
    public ResponseEntity<HttpStatus> deleteHospital(@PathVariable("id") String id){

        try {
            hospitalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hospitals")
    public ResponseEntity<HttpStatus> deleteAllHospitals(){
        try {
            hospitalRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
