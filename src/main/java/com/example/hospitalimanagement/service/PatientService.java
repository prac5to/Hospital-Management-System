package com.example.hospitalimanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospitalimanagement.dto.PaginatedResponseDto;
import com.example.hospitalimanagement.dto.PatientRequestDto;
import com.example.hospitalimanagement.dto.PatientResponseDto;
import com.example.hospitalimanagement.entity.BloodGroup;
import com.example.hospitalimanagement.entity.Insurance;
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
public PaginatedResponseDto<PatientResponseDto> getAllPatients(Integer pageNumber,Integer pageSize)
{
    Pageable p =PageRequest.of(pageNumber,pageSize);
    Page<Patient> pp =pr.findByIsActivetrue(p);

    //Converting entity to dto 
    List<PatientResponseDto> dtoList = pp.getContent().stream().map(this::mapToDto).toList();
    //populate custom PagenatedResponseDto
    PaginatedResponseDto<PatientResponseDto> responseDto = new PaginatedResponseDto<>();
    responseDto.setContent(dtoList);
    responseDto.setPageNumber(pp.getNumber());
    responseDto.setPageSize(pp.getSize());
    responseDto.setTotalElements(pp.getTotalElements());
    responseDto.setTotalPages(pp.getTotalPages());
    responseDto.setLast(pp.isLast());

    return responseDto;

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
    public PatientResponseDto createPatient(PatientRequestDto requestDto)
    {
        Patient patient = mapToEntity(requestDto);//converting dto to entity
        Patient savedPatient = pr.save(patient);
        return mapToDto(savedPatient);//returning dto
    }
/**
     * Update an existing patient.
     */
@Transactional
public PatientResponseDto updatePatient(Long id,PatientRequestDto updatedPatient)
{
 Patient existingData = pr.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

 existingData.setName(updatedPatient.getName());
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
     *///mapToDto only goes one way (from Entity to DTO):
     //Patient (Entity) mapToDto. ->PatientResponseDto (DTO)
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
//When saving a new patient, the data flows in the opposite direction: your controller receives input as a DTO, but your database repository only accepts an Entity:
//PatientRequestDto (DTO) mapToEntity ->Patient (Entity) pr.save()Database
private Patient mapToEntity(PatientRequestDto dto)
{
    if (dto == null)
    {
        return null;
    }
    Patient patient = new Patient();
    patient.setName(dto.getName());
    patient.setGender(dto.getGender());
    patient.setEmail(dto.getEmail());
if(dto.getBloodGroup()!= null)
{
    patient.setBloodGroup(BloodGroup.valueOf(dto.getBloodGroup().toUpperCase()));
}
if(dto.getInsurance() != null)
{
  Insurance insurance = new Insurance(); 
  patient.setInsurance(insurance);
}
    return patient;
}



}
