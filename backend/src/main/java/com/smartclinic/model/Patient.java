package com.smartclinic.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="patients")
public class Patient {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String name;
 @Email @NotBlank @Column(unique=true) private String email;
 @JsonProperty(access=JsonProperty.Access.WRITE_ONLY) @NotBlank private String password;
 @NotBlank private String phone;
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
}