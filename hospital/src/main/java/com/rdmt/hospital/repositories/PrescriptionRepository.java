package com.rdmt.hospital.repositories;

import com.rdmt.hospital.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
