package com.example.hospitalimanagement.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto  
{
    private Long patient_id;
    private String name;
    private String gender;
    private String email;
    private String bloodGroup;
    private String insurance;
}