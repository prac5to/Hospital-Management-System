package com.example.hospitalimanagement.service;

import org.springframework.stereotype.Service;

import com.example.hospitalimanagement.repository.AppointmentRepo;
import com.example.hospitalimanagement.repository.InsuranceRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceService 
{
    public final InsuranceRepo ir;
    public final AppointmentRepo appr;
/*public AppointmentService(InsuranceRepository insuranceRepository, AppointmentRepo appointmentRepo) {
        this.insuranceRepository = insuranceRepository;
        this.appointmentRepo = appointmentRepo;
    }
 */
public void processAppointmentBiling(Long patient_id)
{
    //calling boolean method from repo
    // 1. REPO STEP: Ask database for raw facts
    boolean hasValidInsurance =ir.existsByPatientIdAndIsActiveTrue(patient_id);
    {
// 2. SERVICE STEP: Make business decisions based on that fact
     if(hasValidInsurance)
     {
      System.out.println("Choose your insurance pay:1.CO-PAY "+"2.CO-INSURANCE");
      System.out.println("APPLY $20 CO-PAY");
      System.out.println("APPLY 20% CO-INSURANCE");
     }
     else
     {
     System.out.println("Apply hospital discount 10% on total bill");
     }
    }
}

}
