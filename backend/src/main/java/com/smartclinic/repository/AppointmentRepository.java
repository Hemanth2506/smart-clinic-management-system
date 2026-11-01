package com.smartclinic.repository;
import com.smartclinic.model.Appointment;
import java.time.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
 @Query("select a from Appointment a where a.doctor.id=:id and a.appointmentTime>=:start and a.appointmentTime<:end")
 List<Appointment> byDoctorDate(@Param("id") Long id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
 List<Appointment> findByPatientId(Long patientId);
}