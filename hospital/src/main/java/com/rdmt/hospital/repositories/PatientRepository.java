package com.rdmt.hospital.repositories;

import com.rdmt.hospital.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Busca por correo electrónico
    Optional<Patient> findByEmail(String email);

    // Busca por correo O teléfono
    Optional<Patient> findByEmailOrPhoneNumber(String email, String phoneNumber);
}