package com.rdmt.hospital.controllers;

import com.rdmt.hospital.models.Appointment;
import com.rdmt.hospital.models.Patient;
import com.rdmt.hospital.repositories.AppointmentRepository;
import com.rdmt.hospital.repositories.PatientRepository;
import com.rdmt.hospital.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final TokenService tokenService;

    public PatientController(PatientRepository patientRepository, AppointmentRepository appointmentRepository,
            TokenService tokenService) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        Optional<Patient> patient = patientRepository.findByEmail(credentials.get("email"));
        if (patient.isPresent()) {
            // Genera el token de sesión basándose en el email
            String token = tokenService.generateToken(patient.get().getEmail());
            return ResponseEntity.ok(Map.of("token", token, "patientId", patient.get().getId()));
        }
        return ResponseEntity.status(401).body("Paciente no encontrado");
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<?> getAppointments(
            @RequestHeader("Authorization") String token,
            @PathVariable Long patientId) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Acceso denegado, falta token");
        }
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
}
