package com.smartclinic.controller;
import com.smartclinic.model.*;
import com.smartclinic.repository.*;
import com.smartclinic.service.TokenService;
import java.util.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final PatientRepository patients; private final DoctorRepository doctors; private final PasswordEncoder encoder; private final TokenService tokens;
 public AuthController(PatientRepository patients,DoctorRepository doctors,PasswordEncoder encoder,TokenService tokens){this.patients=patients;this.doctors=doctors;this.encoder=encoder;this.tokens=tokens;}
 @PostMapping("/register/patient") public ResponseEntity<?> register(@RequestBody Patient p){p.setPassword(encoder.encode(p.getPassword()));Patient saved=patients.save(p);return ResponseEntity.status(201).body(Map.of("patientId",saved.getId()));}
 @PostMapping("/login/patient") public ResponseEntity<?> patient(@RequestBody Map<String,String> request){return patients.findByEmail(request.get("email")).filter(p->encoder.matches(request.get("password"),p.getPassword())).<ResponseEntity<?>>map(p->ResponseEntity.ok(Map.of("token",tokens.generateToken(p.getEmail()),"patientId",p.getId()))).orElseGet(()->ResponseEntity.status(401).body(Map.of("error","Invalid credentials")));}
 @PostMapping("/login/doctor") public ResponseEntity<?> doctor(@RequestBody Map<String,String> request){return doctors.findByEmail(request.get("email")).filter(d->encoder.matches(request.get("password"),d.getPassword())).<ResponseEntity<?>>map(d->ResponseEntity.ok(Map.of("token",tokens.generateToken(d.getEmail()),"doctorId",d.getId()))).orElseGet(()->ResponseEntity.status(401).body(Map.of("error","Invalid credentials")));}
}