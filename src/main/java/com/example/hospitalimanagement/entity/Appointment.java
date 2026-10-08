package com.example.hospitalimanagement.entity;
//note -the relationship importants lyes where one relationship has power and can independly 
//exists without other 
//as appointment makes no sense without patient 
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Appointment 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long appointment_id;

 @Column(nullable =false)
 private  LocalDateTime starttime;

 @Column(nullable =false)
 private  LocalDateTime endtime;
 
 @Column(length = 500)
 private String reason;

 @Column(columnDefinition ="TEXT")
 private String doctorNotes;

 @ManyToOne(fetch =FetchType.LAZY)
 @JoinColumn(name ="insurance_id")
 private Insurance insurance;
 //many appointment to one patient 
 @ManyToOne(fetch =FetchType.LAZY)
 @JoinColumn(name ="patient", nullable =false)
 private Patient patient;

 @ManyToOne (fetch =FetchType.LAZY)
 @JoinColumn(name = "doctor", nullable =false)
 private Doctor doctor;
}
