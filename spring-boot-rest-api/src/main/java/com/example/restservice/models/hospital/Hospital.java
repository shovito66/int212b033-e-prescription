package com.example.restservice.models.hospital;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Document(collection = "hospitals")
public class Hospital {

    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    private String hospitalName;
    @NotBlank
    @Size(max = 100)
    private String branchName;
    private String address;
    private String email;
    private String contact;
    private String createdOn;


    public Hospital() {
    }

    public Hospital(@NotBlank @Size(max = 100) String hospitalName,
                    @NotBlank @Size(max = 100) String branchName, String address, String email,
                    String contact, String createdOn) {
        this.hospitalName = hospitalName;
        this.branchName = branchName;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.createdOn = createdOn; //LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    }
}
