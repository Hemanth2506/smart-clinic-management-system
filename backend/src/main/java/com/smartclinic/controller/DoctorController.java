package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import com.smartclinic.service.DoctorService;
import com.smartclinic.service.TokenService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorRepository doctors;
    private final DoctorService service;
    private final TokenService tokens;

    public DoctorController(DoctorRepository doctors, DoctorService service, TokenService tokens) {
        this.doctors = doctors;
        this.service = service;
        this.tokens = tokens;
    }

    @GetMapping
    public List<Doctor> all() {
        return doctors.findAll();
    }

    @GetMapping("/search")
    public List<Doctor> search(@RequestParam String specialty) {
        return doctors.findBySpecialtyIgnoreCase(specialty);
    }

    @GetMapping("/availability/{doctorId}/{date}/{user}/{token}")
    public ResponseEntity<?> availability(@PathVariable Long doctorId, @PathVariable LocalDate date,
                                           @PathVariable String user, @PathVariable String token) {
        try {
            String email = tokens.validateAndGetEmail(token);
            if (!email.equalsIgnoreCase(user)) {
                return ResponseEntity.status(403).body(Map.of("error", "User does not match token"));
            }
            return ResponseEntity.ok(Map.of("doctorId", doctorId, "date", date.toString(),
                    "user", user, "availableSlots", service.availableSlots(doctorId, date)));
        } catch (Exception ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
        }
    }

    @GetMapping("/availability")
    public ResponseEntity<?> availabilityQuery(@RequestHeader("Authorization") String authorization,
                                                @RequestParam Long doctorId, @RequestParam LocalDate date) {
        try {
            tokens.validateAndGetEmail(authorization.replace("Bearer ", ""));
            return ResponseEntity.ok(Map.of("doctorId", doctorId, "date", date.toString(),
                    "availableSlots", service.availableSlots(doctorId, date)));
        } catch (Exception ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
        }
    }
}
