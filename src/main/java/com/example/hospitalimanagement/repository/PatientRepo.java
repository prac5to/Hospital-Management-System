package com.example.hospitalimanagement.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospitalimanagement.entity.Patient;
@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> 
{
    //updated - 1-2 .Combine serach:single serach bar matching Name or Email
    
    
    /*Patient findByName(String name);
//1.fetch paginated list of all active patients
//2.Check if active patient exists with a given email (useful for registration validation)
    boolean existsByEmailAndIsActiveTrue(String email);*/
//we are combinining both 
    Page<Patient> findByIsActivetrue(Pageable pageable);
//3.fetch a single active patient by ID
    Page<Patient> findByIdAndIsActiveTrue(Long id);

//4.Check if active patient exists with a given phone number
//well i dont have a phone number column so we skip it
//boolean existsByPhoneNumberAndIsActiveTrue(String phoneNumber); this is the code for it
//5.Serach patient by name,phone no or email
/*@Query("select p from patient p where p.isActive =true AND 
("+"LOWER(p.name) LIKE LOWER()) */
//Page<Patient> searchActivePatients(@Param ("query ")String query,Pageable pageable);

//6.Fetch distinct active patient who have appointments 
}

