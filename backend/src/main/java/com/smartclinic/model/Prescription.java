package com.smartclinic.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.time.*;
@Document("prescriptions")
public class Prescription {
 @Id private String id;
 @NotNull private Long doctorId;
 @NotNull private Long patientId;
 @NotBlank private String medications;
 private String notes;
 private Instant createdAt=Instant.now();
 public String getId(){return id;} public Long getDoctorId(){return doctorId;} public void setDoctorId(Long v){doctorId=v;}
 public Long getPatientId(){return patientId;} public void setPatientId(Long v){patientId=v;}
 public String getMedications(){return medications;} public void setMedications(String v){medications=v;}
 public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}