package com.example.hospitalimanagement.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* -----RESTful Design: Standard REST architecture maps controllers directly to resource domains:----------------
1.Appointments: POST /api/appointments
2.Doctors: GET /api/doctors/{id}
3.Patients: GET /api/patients/{id}
4.Admin Actions: PUT /api/admin/doctors/{id}/approve -------------------------------------------------------------*/

import com.example.hospitalimanagement.service.PatientService;
import lombok.RequiredArgsConstructor;

//Reserved for administrative tasks like onboard/delete doctors, system reports, and user management (/api/admin).

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController 
{

private final PatientService ps;

@GetMapping("/patient")
public ResponseEntity<List<<PatientResponseDto>> getAllPatients
{
    @RequestParam(value="page",defaultValue ="0") Integer pageNumber,
    @RequestParam(value="size",defaultValue="10") Integer pageSize
}
{
    return ResponseEntity.ok(ps.getAllPatients(pageNumber,pageSize));
}

}
