package com.example.hospitalimanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalimanagement.entity.Doctor;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long>
{
List<Doctor> findBySpecialization(String specialization);
List<Doctor> findByAvaliableTrue();//avaliable wale hi return karega
//or findByAvaliable(boolean avaliable);
//is true and false dono ayega mtlab active or inactive dono return karega
}
