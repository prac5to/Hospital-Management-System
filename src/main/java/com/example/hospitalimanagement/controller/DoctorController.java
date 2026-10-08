package com.example.hospitalimanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalimanagement.service.AppointmentService;

import lombok.RequiredArgsConstructor;
//Handles doctor profiles, viewing assigned patient schedules, and availability (/api/doctors).
@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController 
{
    public final AppointmentService as;

    @GetMapping("/appointments")
    public ResponseEntity<List<<AppointmentResponseDto>>getAllAppointmentDoctor()
 {
    return ResponseEntity.ok(as.getAllAppointmentDoctor(1L));
 }
}
