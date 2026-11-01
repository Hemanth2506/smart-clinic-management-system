package com.smartclinic.repository;
import com.smartclinic.model.Patient;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatientRepository extends JpaRepository<Patient,Long> {
 Optional<Patient> findByEmail(String email);
 Optional<Patient> findByEmailOrPhone(String email,String phone);
}