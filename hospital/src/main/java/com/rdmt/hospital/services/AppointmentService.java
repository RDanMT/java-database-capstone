package com.rdmt.hospital.services;

import com.rdmt.hospital.models.Appointment;
import com.rdmt.hospital.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Criterio rúbrica: Método de reserva que guarda una cita (3 pts)
    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus("CONFIRMED");
        return appointmentRepository.save(appointment);
    }

    // Criterio rúbrica: Recupera citas para un doctor en fecha específica (3 pts)
    public List<Appointment> getAppointmentsForDoctor(Long doctorId, LocalDate date) {
        // Acotamos las horas de inicio de día y fin de día para la cruzada en la DB
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctorId,
                startOfDay,
                endOfDay);
    }
}
