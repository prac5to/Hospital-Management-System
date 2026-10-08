package com.example.hospitalimanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.hospitalimanagement.dto.DoctorResponseDto;
import com.example.hospitalimanagement.entity.Doctor;
import com.example.hospitalimanagement.repository.DoctorRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DoctorService 
{
private final DoctorRepo dr;
public DoctorResponseDto createDoctor(Doctor d)
{
    Doctor savedDoctor =dr.save(d);
    return mapToDto(savedDoctor);
}
public DoctorResponseDto getDoctorById(Long id)
{
    Doctor doctor =dr.findById(id).orElseThrow(() -> new RuntimeException("doctor id  not found"));
    return mapToDto(doctor);
}
public List<DoctorResponseDto> getAllDoctors()
{
    return dr.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
}
public DoctorResponseDto updateAvaliability(Long id,Doctor doctorDetails)
{
    Doctor doctor =dr.findById(id).orElseThrow(() -> new RuntimeException("doctor id  not found"));
    doctor.setName(doctorDetails.getName());
    doctor.setSpecialization(doctorDetails.getSpecialization());
    doctor.setFee(doctorDetails.getFee());

    Doctor updatedDoctors =dr.save(doctorDetails);
    return mapToDto(updatedDoctors);
}
public DoctorResponseDto mapToDto(Doctor doctor)
{
if (doctor ==null) return null;
DoctorResponseDto dto = new DoctorResponseDto();
BeanUtils.copyProperties(doctor,dto);
return dto;
}
}


