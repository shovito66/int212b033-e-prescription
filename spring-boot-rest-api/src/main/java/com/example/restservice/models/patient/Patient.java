package com.example.restservice.models.patient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;

    private String hospitalId;
    private String doctorId;
    private String regDateTime;
    private String DOB;
    @NotBlank
    private  int age;
    @NotBlank
    @Size(max = 1)
    private String gender;
    @Size(max =20)
    private String occupation;
    private  int health_insurance_no;
    @Size(max = 50)
    private String healthCareProvider;
    private  String address;
    private String contact_no;
    private  String createdOn;

    public Patient() {
    }

    public Patient(String hospitalId, String doctorId, String regDateTime, String DOB, @NotBlank int age, @NotBlank @Size(max = 1) String gender, @Size(max = 20) String occupation, int health_insurance_no, @Size(max = 50) String healthCareProvider, String address, String contact_no, String createdOn) {
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.regDateTime = regDateTime;
        this.DOB = DOB;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.health_insurance_no = health_insurance_no;
        this.healthCareProvider = healthCareProvider;
        this.address = address;
        this.contact_no = contact_no;
        this.createdOn = createdOn;
    }
}
