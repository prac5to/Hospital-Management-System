package com.example.hospitalimanagement.service;

import org.springframework.stereotype.Service;
import com.example.hospitalimanagement.dto.AppointmentResponseDto;
import com.example.hospitalimanagement.entity.Appointment;
import com.example.hospitalimanagement.repository.AppointmentRepo;
import com.example.hospitalimanagement.repository.InsuranceRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService 
{
private final AppointmentRepo appr;
private final InsuranceRepo ir;
private static final double STANDARD_CONSULTATION_FEE = 100.00;
// Calculate dynamic fee variable declaration 
 double finalfee;
/*1. CREATE APPOINTMENT (Uses isSlotAlreadyBooked) */
@Transactional
public AppointmentResponseDto createAppointment(Appointment a)
{
    Long patient_id = a.getPatient().getPatient_id();
    Long doctor_id = a.getDoctor().getDoctor_id();
    //Extract the patient id from the incoming 
    // ❌ WRONG (Causes runtime error):
// boolean selfPay = insuranceRepo.isSelfPay();

// ✅ CORRECT:
boolean isSelfPay = ir.existsByPatientIdAndIsSelfPayTrue(patient_id);
boolean hasActiveInsurance =ir.existsByPatientIdAndIsActiveTrue(patient_id);
// Step 1: Insurance check
        if (!hasActiveInsurance && !isSelfPay) 
            {
               throw new IllegalStateException("Patient needs active insurance or must be marked as Self-Pay.");
            }
 // Step 2.1: Calculate dynamic fee
        if(hasActiveInsurance)
        {
           finalfee = STANDARD_CONSULTATION_FEE * 0.20;
        }
        else
        {
           finalfee = STANDARD_CONSULTATION_FEE * 0.90;
        }


//Step 2: Use the @Query repository method to check for time overlap
    boolean slotTaken =appr.existsByDoctorIDAndStartTimeLessThanAndEndTimeGreaterThan(
        doctor_id,a.getEndtime(),a.getStarttime());
// ❌ WRONG: Passing Doctor object into a Long parameter
//a.getDoctor();
// ✅ CORRECT: If your Appointment entity directly stores doctorId as a Long
//a.getDoctorId();
    if(slotTaken)
    {  throw new IllegalStateException("slot taken ");}
     Appointment savedapp=appr.save(a);
//convert Entity->DTO
AppointmentResponseDto dto = new AppointmentResponseDto();
//you are fetching (getting) the saved values from your database Entity and populating (setting) them into the DTO object so
//  Spring can convert it into a JSON response for the frontend.
dto.setAppointment_id(savedapp.getAppointment_id());
dto.setDoctor_id(savedapp.getDoctor().getDoctor_id());
dto.setPatient_id(savedapp.getPatient().getPatient_id());

        return dto;
}




}
