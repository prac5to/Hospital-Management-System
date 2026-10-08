package com.example.hospitalimanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalimanagement.entity.Insurance;
@Repository
public interface InsuranceRepo extends JpaRepository<Insurance,Long>
{
    // Returns true if an active insurance record exists for this patient
boolean existsByPatientIdAndIsActiveTrue(Long patient_id);
// Optional: Check if a specific policy number exists and is active
boolean existsByPolicyNumberAndIsActiveTrue(String policy_no);
// Returns true if the patient's record is marked as Self-Pay
boolean existsByPatientIdAndIsSelfPayTrue(Long patient_id);
// Returns true if patient has active insurance AND is not self-pay
boolean existsByPatientIdAndIsActiveTrueAndIsSelfPayFalse(Long patient_id);
}
