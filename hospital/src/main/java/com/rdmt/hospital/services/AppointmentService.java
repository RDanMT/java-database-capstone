package com.rdmt.hospital.services;

import com.rdmt.hospital.models.Appointment;
import com.rdmt.hospital.models.Doctor;
import com.rdmt.hospital.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment save(Appointment appointment) {
        return repository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctorAndDate(Doctor doctor, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);
        return repository.findByDoctorAndAppointmentTimeBetween(doctor, start, end);
    }
}
