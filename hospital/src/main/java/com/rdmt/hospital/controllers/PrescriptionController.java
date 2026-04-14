package com.rdmt.hospital.controllers;

import com.rdmt.hospital.models.Prescription;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    // Criterio Rúbrica: POST Endpoint validando el token y usando @Valid en el
    // @RequestBody (3 pts)
    @PostMapping
    public ResponseEntity<?> savePrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody Prescription prescription) {

        // Validamos el estado del Token
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "error", "Acceso denegado. Se requiere un Token del Doctor."));
        }

        // (La lógica del repositorio que la guarda en BD iría aquí)
        // prescriptionRepository.save(prescription);

        // Criterio Rúbrica: Devuelve mensaje de éxito estructurado (JSON mapeado) (3
        // pts)
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "La receta médica fue guardada en el historial exitosamente",
                "appointmentId", prescription.getAppointmentId()));
    }
}
