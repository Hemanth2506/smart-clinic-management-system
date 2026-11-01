package com.smartclinic.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {
    List<DoctorAvailability> findByDoctor_IdAndAvailableDate(Long doctorId, LocalDate availableDate);
}
