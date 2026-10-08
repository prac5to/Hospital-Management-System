package com.example.hospitalimanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long doctor_id;

@Column(nullable =false)
private String name;

@Column(nullable =false)
private String specialization;

@Column(nullable =false,unique =true)
private String email;

@Column(nullable = false,unique =true)
private boolean avaliable; 

@Column(nullable = false,unique =true)
private Double fee;

@OneToMany(mappedBy = "appointment")
private Appointment appointment;

@ManyToOne
@JoinColumn(name  ="department_id")//java field name in other entity
private Department department;

}
