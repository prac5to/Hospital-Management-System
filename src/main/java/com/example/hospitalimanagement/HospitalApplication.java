package com.example.hospitalimanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
}
//No Hospital entity/repo exists in your code.so HospitalControlle is not needed
//Conditional InsuranceController-Needed if you have API endpoints to  add, renew, or verify insurance policies. 
// Skip if insurance is only modified internally inside patient profile updates.