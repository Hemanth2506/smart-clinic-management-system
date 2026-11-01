package com.smartclinic.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;
@Entity @Table(name="doctors")
public class Doctor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String name;
 @Email @NotBlank @Column(unique=true) private String email;
 @NotBlank private String password;
 @NotBlank private String specialty;
 @ElementCollection(fetch=FetchType.EAGER) @CollectionTable(name="doctor_times",joinColumns=@JoinColumn(name="doctor_id")) @Column(name="available_time") private List<String> availableTimes=new ArrayList<>();
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getSpecialty(){return specialty;} public void setSpecialty(String v){specialty=v;} public List<String> getAvailableTimes(){return availableTimes;} public void setAvailableTimes(List<String> v){availableTimes=v;}
}