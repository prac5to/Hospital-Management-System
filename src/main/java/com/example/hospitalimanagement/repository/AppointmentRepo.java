package com.example.hospitalimanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hospitalimanagement.entity.Appointment;
import com.example.hospitalimanagement.entity.Patient;
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long>
{
    // Fetch distinct patients for a doctor (with pagination)
    @Query("SELECT DISTINCT a.patient FROM Appointment a WHERE a.doctor.id = :doctorId")
    Page<Patient> findDistinctPatientsByDoctorId1(@Param("doctorId") Long doctorId, Pageable pageable);
  
  // OR without pagination (plain List):
    @Query("SELECT DISTINCT a.patient FROM Appointment a WHERE a.doctor.email = :email")
    Page<Patient>findDistinctPatientsByDoctorEmail(@Param("email") String email ,Pageable pageable);
  
  
  // 1. Fetch all appointments for a specific doctor
  //List<Appointment> findByDoctorId(Long doctor_id);
  // 1. Unbounded history -> Page is safer
  Page<Appointment>findByDoctorId(Long doctor_id,Pageable pageable);
  // 2. Fetch all appointments for a specific patient
  //List<Appointment> findByPatientId(Long patient_id);
  Page<Appointment>findByPatientId(Long patient_id,Pageable pageable);
  // 3.Fetch appointments for doctor between 2 dates/times
  List<Appointment> findByDoctorIdAndStartTimeBetween(
    Long doctor_id,LocalDateTime start,LocalDateTime end
  );
  // 4. Auto-generated check for overlapping appointments (Double-booking check)
  boolean existsByDoctorIDAndStartTimeLessThanAndEndTimeGreaterThan(
    Long doctor_id,LocalDateTime startTime,LocalDateTime endTime
  );
  
}
