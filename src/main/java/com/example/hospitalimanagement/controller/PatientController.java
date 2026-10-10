package com.example.hospitalimanagement.controller;

import org.springframework.http.HttpStatus;

//import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalimanagement.dto.PaginatedResponseDto;
import com.example.hospitalimanagement.dto.PatientRequestDto;
import com.example.hospitalimanagement.dto.PatientResponseDto;
import com.example.hospitalimanagement.service.PatientService;

import lombok.RequiredArgsConstructor;
//Handles patient profiles, registration, and personal history (/api/patients).
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController 
{
     private final PatientService ps;
// paginated list of active patients ?pageNumber=0&pageSize=5
@GetMapping //url at Get/api/patients
public ResponseEntity<PaginatedResponseDto<PatientResponseDto>> getAllPatients(
    @RequestParam(defaultValue ="0") Integer pageNumber,
    @RequestParam(defaultValue ="10") Integer pageSize)
{
       PaginatedResponseDto<PatientResponseDto> response = ps.getAllPatients(pageNumber,pageSize);
       return ResponseEntity.ok(response);
}
//get patient by id
@GetMapping("/{id}")
public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id)
{
    PatientResponseDto response =ps.getPatientDtoById(id);
    return ResponseEntity.ok(response);
}
//create new patient
@PostMapping("/{id}")
public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto requestDto)
{
    PatientResponseDto response = ps.createPatient(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(response);                 
}
//update existing patient
@PutMapping("/{id}")
public ResponseEntity<PatientResponseDto> updatePatient
(@PathVariable Long id,@RequestBody PatientRequestDto updatedPatient)
{
    PatientResponseDto response = ps.updatePatient(id,updatedPatient);
    return ResponseEntity.ok(response);
}
//delete patient by id
@DeleteMapping("/{id}")
public ResponseEntity<PatientResponseDto> deletePatient(@PathVariable Long id)
{
    PatientResponseDto response = ps.deletePatient(id);
    return ResponseEntity.ok(response);
}




    }
