package com.example.hospitalimanagement.dto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
    private Long patient_id;
    private String name;
    private String gender;
    private String email;
    private LocalDateTime createdTime;
    private String bloodGroup;
    private String insurance;
}
