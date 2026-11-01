package com.smartclinic.service;
import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import java.time.LocalDate;
import java.util.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class DoctorService {
 private final DoctorRepository doctors; private final PasswordEncoder encoder;
 public DoctorService(DoctorRepository doctors,PasswordEncoder encoder){this.doctors=doctors;this.encoder=encoder;}
 public List<String> availableSlots(Long doctorId,LocalDate date){return doctors.findById(doctorId).map(Doctor::getAvailableTimes).orElse(List.of());}
 public ResponseEntity<?> login(String email,String password){return doctors.findByEmail(email).filter(d->encoder.matches(password,d.getPassword())).<ResponseEntity<?>>map(d->ResponseEntity.ok(Map.of("doctorId",d.getId(),"message","Login successful"))).orElseGet(()->ResponseEntity.status(401).body(Map.of("error","Invalid credentials")));}
}