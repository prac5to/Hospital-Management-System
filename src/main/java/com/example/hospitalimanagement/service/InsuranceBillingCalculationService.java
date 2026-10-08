package com.example.hospitalimanagement.service;

import org.springframework.stereotype.Service;
import com.example.hospitalimanagement.repository.InsuranceRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceBillingCalculationService 
{
    private final InsuranceRepo ir;
// Insurance pays 80%
 private static final double insurance_coverage_rate =0.80;
 //We define it this way instead of writing 0.80 directly in the code (known as a "magic number") for two main reasons:
 /*Readability: (1.0 - INSURANCE_COVERAGE_RATE) clearly tells anyone reading the code what 0.80 actually stands for.
Maintainability: If the hospital changes its insurance copay rate from 80% to 85% next year, 
you only change 0.80 to 0.85 in one single line at the top of your class, instead of hunting through every method to update raw numbers.
  */
 // 10% cash discount
 private static final double self_pay_discount_rate = 0.10;
// Injected from application.properties (with fallback defaults) Use @Value("${property.key:default_value}")
   
//note -this is a Custom Keys (Created by You):@Value("${hospitalimanagement.service.insurancebillingcalculationservice.insurance-coverage-rate:0.80}")
//private double insuranceCoverageRate;

    
//@Value("${hospital.billing.self-pay-discount-rate:0.10}")
//private double selfPayDiscountRate;

//@Value("${hospital.billing.standard-consultation-fee:100.00}")
//private double standardConsultationFee;

 public double calculatePayableAmount(Long patient_id,
    double baseconsultationfee)
{
boolean hasActiveInsurance =ir.existsByPatientIdAndIsActiveTrue(patient_id);
boolean isSelfPay =ir.existsByPatientIdAndIsSelfPayTrue(patient_id);
 
if(!hasActiveInsurance && !isSelfPay)
{
    throw new IllegalStateException("Patient needs active insurance or must be marked as Self-Pay.");
}
if(hasActiveInsurance)
{
//Why (1.0 - INSURANCE_COVERAGE_RATE) and what is 1.0?
//1.0 represents 100% of the full bill in decimal form (100%=1.0)
    return baseconsultationfee * (1.0 -insurance_coverage_rate);
//When calculating what the patient owes:
//Insurance Coverage Rate: Insurance pays 80% (0.80).
//Patient Copay: The patient pays whatever is left of the total 100%.
//Patient Portion=100%−80%=20%⟶(1.0−0.80)=0.20
}
else
{
    return baseconsultationfee * (1.0 -self_pay_discount_rate);
}
}
}
