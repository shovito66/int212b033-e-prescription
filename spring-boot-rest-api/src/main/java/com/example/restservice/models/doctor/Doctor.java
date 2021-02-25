package com.example.restservice.models.doctor;

import com.example.restservice.models.hospital.Hospital;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Optional;

@Getter
@Setter
@ToString
@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;
    @NotBlank
    @NotEmpty
    private Hospital hospitalID;
    @NotBlank
    @Size(max = 100)
    private String doctorName;
    @NotBlank
    @Size(max = 100)
    @NotEmpty private String speciality;
    private String address;
    @Size(max = 100)
    private String about;
    private String profile_picture; //can be blank
    private String createdOn;

    public Doctor() {
    }

    public Doctor(@NotBlank @NotEmpty Hospital hospitalID, @NotBlank @Size(max = 100) String doctorName, @NotBlank @Size(max = 100) @NotEmpty String speciality, String address, @Size(max = 100) String about, String profile_picture, String createdOn) {
        this.hospitalID = hospitalID;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.address = address;
        this.about = about;
        this.profile_picture = profile_picture;
        this.createdOn = createdOn;
    }

    public Doctor(@NotBlank @NotEmpty Hospital hospitalID, @NotBlank @Size(max = 100) String doctorName, @NotBlank @Size(max = 100) @NotEmpty String speciality, String address, @Size(max = 100) String about, String createdOn) {
        this.hospitalID = hospitalID;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.address = address;
        this.about = about;
        this.createdOn = createdOn;
        /**
         *
         * profile Pic upload:
         *          1) https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
         *         2) https://www.youtube.com/watch?v=Hef5pJkNCvA
         */


    }
}
