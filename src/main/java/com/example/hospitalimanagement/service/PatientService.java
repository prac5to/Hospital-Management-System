package com.example.hospitalimanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospitalimanagement.dto.PatientResponseDto;
import com.example.hospitalimanagement.entity.Patient;
import com.example.hospitalimanagement.repository.PatientRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService 
{
private final PatientRepo pr;//dependancy injection
     /* 
     * Retrieves a paginated list of patients converted to DTOs.
     * Matches the endpoint in PatientController.
     */
public  List<PatientResponseDto> getAllPatients(Integer pageNumber,Integer pageSize)
{
                Pageable p = PageRequest.of(pageNumber,pageSize);
                Page<Patient> pp =pr.findAll(p);

return pp.getContent().stream().map(this::mapToDto).toList();
}
/**
     * Demonstrates Hibernate First-Level (L1) Cache inside @Transactional.
     * p1 and p2 refer to the exact same object instance in memory (p1 == p2 is true).
     */
//@Transactional
//public PatientResponseDto getPatientById(Long id)
/*{
    Patient p1= pr.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    Patient p2= pr.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    System.out.println(p1==p2);
    p1.setName("prince");// Dirty checking still runs!
    return mapToDto(p1);//It is a custom helper method that you write inside your service class (or in a separate Mapper class)
    //  to copy field values from your Patient entity into a new PatientResponseDto object.
}*/
/**
     * Get single patient mapped to DTO.
     */

public PatientResponseDto getPatientDtoById(Long id)
{
    Patient p = pr.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    return mapToDto(p);
}
    /**
     * Create a new patient.
     */
    @Transactional
    public PatientResponseDto createPatient(Patient p)
    {
        Patient savedPatient = pr.save(p);
        return mapToDto(savedPatient);
    }
/**
     * Update an existing patient.
     */
@Transactional
public PatientResponseDto updatePatient(Long id,Patient updatedData)
{
 Patient existingData = pr.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

 existingData.setName(updatedData.getName());
 Patient savedPatient = pr.save(existingData);
 return mapToDto(savedPatient);
}
/**
     * Delete a patient by ID.
     */
@Transactional
public PatientResponseDto deletePatient(Long id)
{
    Patient p = pr.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

    if(!pr.existsById(id))
    {
        throw new RuntimeException("Patient not found with id: " + id);
    }
    pr.deleteById(id);// YE DATABASE SE DATA DELETE KARTA HAI:
    return mapToDto(p);
}
/* 
     * Helper method to map Patient Entity to PatientResponseDto.
     */
private PatientResponseDto mapToDto(Patient p)
{
    if (p == null) 
    {
        return null;
    }
    PatientResponseDto prdto = new PatientResponseDto();
    prdto.setPatient_id(p.getPatient_id());
    prdto.setName(p.getName());
    // Map remaining fields according to your PatientResponseDto class
        return prdto;
}
}
