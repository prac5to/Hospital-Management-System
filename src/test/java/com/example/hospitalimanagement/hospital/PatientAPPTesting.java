package com.example.hospitalimanagement.hospital;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//junit helps to test application without running apis

import com.example.hospitalimanagement.dto.PatientResponseDto;
import com.example.hospitalimanagement.entity.Patient;
import com.example.hospitalimanagement.repository.PatientRepo;
import com.example.hospitalimanagement.service.PatientService;

@SpringBootTest
public class PatientAPPTesting 
{
    @Autowired
    //now we have to run the repository so we put autowired
    private  PatientRepo pr;
    @Autowired
    private PatientService ps;
@Test
//note in test method the return type must be void
public void testPatientRepository()
{

    List<Patient> patientList = pr.findAll();
    System.out.println(patientList);
}
@Test
public void testPatientGetById()
{
    Long id =1L;
 PatientResponseDto p =ps.getPatientDtoById(id);
 System.out.println(p);
}
@Test
public void testTransactionMethods()
{
pr.findByName("diya patel");
}

}
