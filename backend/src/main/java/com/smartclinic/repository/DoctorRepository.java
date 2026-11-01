package com.smartclinic.repository;
import com.smartclinic.model.Doctor;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
 Optional<Doctor> findByEmail(String email);
 List<Doctor> findBySpecialtyIgnoreCase(String specialty);
 List<Doctor> findByNameContainingIgnoreCase(String name);
}