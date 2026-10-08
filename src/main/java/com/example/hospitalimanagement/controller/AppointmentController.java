package com.example.hospitalimanagement.controller;

// 1. AppointmentController - Used by Patients and Doctors

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalimanagement.service.AppointmentService;

import lombok.RequiredArgsConstructor;

//Handles patient booking, cancellation, and doctor schedule lookups (/api/appointments).

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController 
{
private final AppointmentService apps;

@PostMapping

}