package com.example.hospitalimanagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InsuranceResponseDto
{
    private Long insurance_id;
    private String policy_no;
    private String provider;
    private LocalDate valid_until;
    private LocalDateTime created_at;
    private boolean isActive;
    private  Double copay;
 // Fixed flat fee per visit (e.g., 20.00)

 private Double annualDeductable;
// Total yearly deductible limit (e.g., 1000.00)

 private Double remainingDeductable;
// Deductible amount left to pay this year

 private Double coinsurance;
// Patient percentage after deductible (e.g., 0.20 = 20%)

private Double remainingOutOfPocket;
//Remaining spending cap left for current year

 private boolean selfPay = false;
 // Add this field for self-paying patients
}



