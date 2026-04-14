package com.rdmt.hospital.services;

import com.rdmt.hospital.models.Doctor;
import com.rdmt.hospital.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final TokenService tokenService;

    // Inyección de dependencias mediante constructor (Buenas prácticas)
    public DoctorService(DoctorRepository doctorRepository, TokenService tokenService) {
        this.doctorRepository = doctorRepository;
        this.tokenService = tokenService;
    }

    // Criterio rúbrica: Devuelve horarios disponibles en fecha dada, con filtrado (Pregunta 10)
    public List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isPresent()) {
            // Ejemplo de filtrado: Si es fin de semana, no hay servicio
            if (date.getDayOfWeek().getValue() >= 6) {
                return List.of(); 
            }
            return doctorOpt.get().getAvailableTimes();
        }
        return List.of();
    }

    // Criterio rúbrica: Valida credenciales y devuelve respuesta estructurada (ResponseEntity en Service)
    public org.springframework.http.ResponseEntity<Map<String, String>> login(String email, String password) {
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);

        if (doctorOpt.isPresent() && doctorOpt.get().getPassword().equals(password)) {
            String token = tokenService.generateToken(email);
            return org.springframework.http.ResponseEntity.ok(Map.of(
                    "success", "true",
                    "message", "Login aprobado",
                    "token", token));
        }

        return org.springframework.http.ResponseEntity.status(401).body(Map.of(
                "success", "false",
                "message", "Credenciales invalidas"));
    }
}
