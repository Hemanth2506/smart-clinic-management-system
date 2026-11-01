package com.smartclinic.controller;
import com.smartclinic.model.Appointment;
import com.smartclinic.service.*;
import jakarta.validation.Valid;
import java.time.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/appointments")
public class AppointmentController {
 private final AppointmentService service; private final TokenService tokens;
 public AppointmentController(AppointmentService service,TokenService tokens){this.service=service;this.tokens=tokens;}
 @PostMapping public ResponseEntity<?> book(@RequestHeader("Authorization") String auth,@Valid @RequestBody Appointment appointment){tokens.validateAndGetEmail(auth.replace("Bearer ",""));return ResponseEntity.status(201).body(service.bookAppointment(appointment));}
 @GetMapping("/doctor/{id}") public List<Appointment> doctor(@RequestHeader("Authorization") String auth,@PathVariable Long id,@RequestParam LocalDate date){tokens.validateAndGetEmail(auth.replace("Bearer ",""));return service.getAppointmentsForDoctor(id,date);}
 @GetMapping("/patient/{id}") public List<Appointment> patient(@RequestHeader("Authorization") String auth,@PathVariable Long id){tokens.validateAndGetEmail(auth.replace("Bearer ",""));return service.getAppointmentsForPatient(id);}
}