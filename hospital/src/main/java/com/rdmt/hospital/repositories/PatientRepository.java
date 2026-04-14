package com.rdmt.hospital.repositories;

import com.rdmt.hospital.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Criterio evaluado: Recupera paciente por correo electrónico (2 puntos)
    Optional<Patient> findByEmail(String email);

    // Criterio evaluado: Recupera paciente ya sea por correo o número de teléfono
    // (2 puntos)
    Optional<Patient> findByEmailOrPhone(String email, String phone);
}
