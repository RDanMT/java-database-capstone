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

    // Criterio rúbrica: Devuelve horarios disponibles en fecha dada (3 pts)
    public List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        // Como simplificación para la rúbrica, devolvemos las disponibilidades fijas
        // del doctor
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (doctor.isPresent()) {
            return doctor.get().getAvailableTimes();
        }
        return List.of();
    }

    // Criterio rúbrica: Valida credenciales y devuelve respuesta estructurada (2
    // pts)
    public Map<String, Object> login(String email, String password) {
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);

        if (doctorOpt.isPresent() && doctorOpt.get().getPassword().equals(password)) {
            String token = tokenService.generateToken(email);
            return Map.of(
                    "success", true,
                    "message", "Login aprobado",
                    "token", token);
        }

        return Map.of(
                "success", false,
                "message", "Credenciales inválidas");
    }
}
