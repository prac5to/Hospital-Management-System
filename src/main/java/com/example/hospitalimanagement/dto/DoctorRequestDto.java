package com.example.hospitalimanagement.dto;

import lombok.Data;

@Data
public class DoctorRequestDto 
{
    private Long doctor_id;
    private String name;
    private String specialization;
    private boolean avaliable; 
    private Double fee;
    private String email;
}
