package com.smartclinic.repository;
import com.smartclinic.model.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface PrescriptionRepository extends MongoRepository<Prescription,String>{}