package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctors;
    private final DoctorAvailabilityRepository availability;
    private final PasswordEncoder encoder;
    private final TokenService tokens;

    public DoctorService(DoctorRepository doctors, DoctorAvailabilityRepository availability,
                         PasswordEncoder encoder, TokenService tokens) {
        this.doctors = doctors;
        this.availability = availability;
        this.encoder = encoder;
        this.tokens = tokens;
    }

    public List<String> availableSlots(Long doctorId, LocalDate date) {
        return availability.findByDoctor_IdAndAvailableDate(doctorId, date).stream()
                .map(slot -> slot.getStartTime() + " - " + slot.getEndTime())
                .toList();
    }

    public ResponseEntity<Map<String, String>> login(Login request) {
        return doctors.findByEmail(request.email())
                .filter(doctor -> encoder.matches(request.password(), doctor.getPassword()))
                .<ResponseEntity<Map<String, String>>>map(doctor ->
                        ResponseEntity.ok(Map.of("token", tokens.generateToken(doctor.getEmail()),
                                "doctorId", doctor.getId().toString(), "message", "Login successful")))
                .orElseGet(() -> ResponseEntity.status(401)
                        .body(Map.of("error", "Invalid credentials")));
    }

    public ResponseEntity<Map<String, String>> login(String email, String password) {
        return login(new Login(email, password));
    }
}
