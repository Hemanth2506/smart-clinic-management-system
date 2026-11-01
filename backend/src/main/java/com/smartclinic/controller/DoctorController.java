package com.smartclinic.controller;
import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import com.smartclinic.service.*;
import java.time.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/doctors")
public class DoctorController {
 private final DoctorRepository repo; private final DoctorService service; private final TokenService tokens;
 public DoctorController(DoctorRepository repo,DoctorService service,TokenService tokens){this.repo=repo;this.service=service;this.tokens=tokens;}
 @GetMapping public List<Doctor> all(){return repo.findAll();}
 @GetMapping("/availability") public ResponseEntity<?> availability(@RequestHeader("Authorization") String auth,@RequestParam Long doctorId,@RequestParam LocalDate date){
 try{tokens.validateAndGetEmail(auth.replace("Bearer ",""));return ResponseEntity.ok(Map.of("doctorId",doctorId,"date",date.toString(),"availableSlots",service.availableSlots(doctorId,date)));}
 catch(Exception e){return ResponseEntity.status(401).body(Map.of("error","Invalid token"));}}
 @GetMapping("/search") public List<Doctor> search(@RequestParam String specialty){return repo.findBySpecialtyIgnoreCase(specialty);}
}