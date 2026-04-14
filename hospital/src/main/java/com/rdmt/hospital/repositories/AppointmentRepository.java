package com.rdmt.hospital.repositories;

import com.rdmt.hospital.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Un derived-query para buscar citas por Doctor dentro de un rango (un día)
    List<Appointment> findByDoctorIdAndAppointmentTimeBetween(
            Long doctorId,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay);

    List<Appointment> findByPatientId(Long patientId);

}
