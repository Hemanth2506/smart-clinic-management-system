package com.smartclinic.service;
import jakarta.persistence.*;
import java.time.*;
@Entity
@Table(name = "doctor_availability")
public class DoctorAvailability {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="doctor_id")
    private com.smartclinic.model.Doctor doctor;
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    public Long getId() { return id; }
    public Long getDoctorId() { return doctor.getId(); }
    public LocalDate getAvailableDate() { return availableDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setDoctor(com.smartclinic.model.Doctor value) { doctor=value; }
    public void setAvailableDate(LocalDate value) { availableDate=value; }
    public void setStartTime(LocalTime value) { startTime=value; }
    public void setEndTime(LocalTime value) { endTime=value; }
}
