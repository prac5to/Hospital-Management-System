package com.example.hospitalimanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalimanagement.dto.PatientResponseDto;
import com.example.hospitalimanagement.service.PatientService;

import lombok.RequiredArgsConstructor;
//Handles patient profiles, registration, and personal history (/api/patients).
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService ps;
// View specific patient details (Used by Doctor / Receptionist / Staff)
    @GetMapping("/{id}")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        List<PatientResponseDto> patients = ps.getAllPatients(pageNumber, pageSize);
        return ResponseEntity.ok(patients);
    }
}