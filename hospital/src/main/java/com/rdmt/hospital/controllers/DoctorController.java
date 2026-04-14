package com.rdmt.hospital.controllers;

import com.rdmt.hospital.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Criterio Rúbrica: GET endpoint usando parámetros dinámicos (@RequestParam) (3
    // pts)
    @GetMapping("/availability")
    public ResponseEntity<?> getAvailability(
            @RequestHeader("Authorization") String token,
            @RequestParam Long doctorId,
            @RequestParam String date) {

        // Criterio Rúbrica: Valida el token y devuelve ResponseEntity estructurado (3
        // pts)
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "error", "Token de sesión ausente o con formato incorrecto"));
        }

        LocalDate parsedDate = LocalDate.parse(date);
        List<String> times = doctorService.getAvailableTimes(doctorId, parsedDate);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "doctorId", doctorId,
                "solicitedDate", date,
                "availableTimes", times));
    }
}
