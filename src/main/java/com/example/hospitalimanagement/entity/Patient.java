package com.example.hospitalimanagement.entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
@Entity
@Data
@Table ( //datyabase table config
    name = "Patient",
    uniqueConstraints = { @UniqueConstraint(name ="unique_patient_email",columnNames={"email","patient_name"})//, 
                          //@UniqueConstraint(name ="unique_patient_name",columnNames={"name"})
                        },indexes ={
                                     @Index(name="email_indx",columnList ="email")
                                     /*The database name is not written because JPA does not need you to specify the database name in @Index.
The database is already associated with your application through your JPA/Hibernate configuration (for example, your application.properties or application.yml file).
 Hibernate knows which database it is connected to and creates the index there. */
                                   }
        )
public class Patient
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long patient_id;
@Column(name="patient_name",nullable =false,length =40)
private String name;
private String gender;
private  LocalDate dob;
@Column(unique = true)
private String email;
//filled with current time 
@Column(updatable=false)
private LocalDateTime createdtime;
//all these are enforced at database level so cannot update it at database level also
@Enumerated(EnumType.STRING)//by default ordinal stores string 
private BloodGroup bloodGroup;

@OneToOne  //(orphanRemoval)
@JoinColumn(name = "insurance")//where therre is joining side we have foreign key there that will be owning side
private Insurance insurance;//entity attribute not marked with association annotation

//Appointment owns the relationship because it has:@JoinColumn(name = "patient_id")
//if u want to have unidirectional just dont mentioneand create any relationshiop mapping
@JsonIgnore
@OneToMany(mappedBy="patient")
private List<Appointment> appointment;
}
