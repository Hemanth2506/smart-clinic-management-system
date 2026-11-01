package com.smartclinic.controller;
import com.smartclinic.model.Prescription;
import com.smartclinic.repository.PrescriptionRepository;
import com.smartclinic.service.TokenService;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/prescriptions")
public class PrescriptionController {
 private final PrescriptionRepository repo; private final TokenService tokens;
 public PrescriptionController(PrescriptionRepository repo,TokenService tokens){this.repo=repo;this.tokens=tokens;}
 @PostMapping("/{token}") public ResponseEntity<Map<String,String>> create(@PathVariable String token,@Valid @RequestBody Prescription prescription){
 try{tokens.validateAndGetEmail(token);Prescription saved=repo.save(prescription);return ResponseEntity.status(201).body(Map.of("message","Prescription saved","id",saved.getId()));}
 catch(Exception e){return ResponseEntity.status(401).body(Map.of("error","Unauthorized or invalid request"));}}
}