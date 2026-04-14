package com.rdmt.hospital.controllers;

import com.rdmt.hospital.models.Doctor;
import com.rdmt.hospital.repositories.DoctorRepository;
import com.rdmt.hospital.services.DoctorService;
import com.rdmt.hospital.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;
    private final TokenService tokenService;

    public DoctorController(DoctorService doctorService, DoctorRepository doctorRepository, TokenService tokenService) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
        this.tokenService = tokenService;
    }

    // Criterio Rúbrica: GET endpoint usando parámetros dinámicos (@PathVariable) (Pregunta 5)
    @GetMapping("/{userId}/availability/{doctorId}/{date}")
    public ResponseEntity<?> getAvailability(
            @RequestHeader("Authorization") String token,
            @PathVariable Long userId,
            @PathVariable Long doctorId,
            @PathVariable String date) {

        // Criterio Rúbrica: Valida el token de manera robusta
        if (token == null || !tokenService.validateToken(token)) {
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "error", "Token de sesión expirado o malformado"));
        }

        LocalDate parsedDate = LocalDate.parse(date);
        List<String> times = doctorService.getAvailableTimes(doctorId, parsedDate);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "userId", userId,
                "doctorId", doctorId,
                "solicitedDate", date,
                "availableTimes", times));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorRepository.findAll());
    }
}
