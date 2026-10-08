package com.example.hospitalimanagement.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance 
{
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(nullable = false,unique =true)
 private Long insurance_id;

 @Column(nullable = false,unique =true)
 String policy_no;

 @Column(nullable = false,unique =true)
 String provider;

 @Column(nullable = false)
 LocalDate valid_until;

 @Column(nullable = false)
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

// Add this field for self-paying patients
@Column(nullable =false)
 private boolean isSelfPay = false;
 
 @CreationTimestamp
 @Column(nullable =false,updatable =false)
 private LocalDateTime created_at;

 @OneToOne(mappedBy ="insurance")//there is bi directional mapping required as we want
 //to find patient from insurance as well as one insurance is mapped to one patient
 //hence we gave one to one mapping here and we dont want insurance to be owner so we set mappedby to insurance 
 private Patient patient;

}
