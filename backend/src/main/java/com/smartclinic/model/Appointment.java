package com.smartclinic.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.*;
@Entity @Table(name="appointments")
public class Appointment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="doctor_id") private Doctor doctor;
 @ManyToOne(optional=false) @JoinColumn(name="patient_id") private Patient patient;
 @NotNull @Future private LocalDateTime appointmentTime;
 private String status="BOOKED";
 public Long getId(){return id;} public Doctor getDoctor(){return doctor;} public void setDoctor(Doctor v){doctor=v;}
 public Patient getPatient(){return patient;} public void setPatient(Patient v){patient=v;}
 public LocalDateTime getAppointmentTime(){return appointmentTime;} public void setAppointmentTime(LocalDateTime v){appointmentTime=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
}