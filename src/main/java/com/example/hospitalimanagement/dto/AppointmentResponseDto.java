package com.example.hospitalimanagement.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AppointmentResponseDto 
{
private Long appointment_id;
private Long patient_id;
private Long insurance_id;
private Long doctor_id;
private  LocalDateTime appointmentstarttime;
private  LocalDateTime appointmentendtime;
private String reason;
private String doctorNotes;
}
