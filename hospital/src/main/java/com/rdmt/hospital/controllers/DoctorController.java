package com.rdmt.hospital.controllers;

import com.rdmt.hospital.models.Doctor;
import com.rdmt.hospital.repositories.DoctorRepository;
import com.rdmt.hospital.services.DoctorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorService doctorService, DoctorRepository doctorRepository) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getAvailability(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        // Validación de token ficticia
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(doctorService.getAvailableTimes(id));
    }

    @GetMapping("/search")
    public List<Doctor> searchBySpecialty(@RequestParam String specialty) {
        return doctorRepository.findAll().stream()
                .filter(d -> d.getSpecialty().equalsIgnoreCase(specialty))
                .collect(Collectors.toList());
    }
}
