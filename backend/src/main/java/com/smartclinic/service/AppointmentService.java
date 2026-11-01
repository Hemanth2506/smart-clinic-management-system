package com.smartclinic.service;
import com.smartclinic.model.Appointment;
import com.smartclinic.repository.AppointmentRepository;
import java.time.*;
import java.util.*;
import org.springframework.stereotype.Service;
@Service
public class AppointmentService {
 private final AppointmentRepository repo;
 public AppointmentService(AppointmentRepository repo){this.repo=repo;}
 public Appointment bookAppointment(Appointment request){return repo.save(request);}
 public List<Appointment> getAppointmentsForDoctor(Long doctorId,LocalDate date){return repo.byDoctorDate(doctorId,date.atStartOfDay(),date.plusDays(1).atStartOfDay());}
 public List<Appointment> getAppointmentsForPatient(Long patientId){return repo.findByPatientId(patientId);}
}