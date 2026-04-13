package com.rdmt.hospital.services;

import com.rdmt.hospital.models.Doctor;
import com.rdmt.hospital.repositories.DoctorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<LocalDateTime> getAvailableTimes(Long id) {
        if (id == null) {
            return Collections.emptyList();
        }

        return doctorRepository.findById(id)
                .map(Doctor::getAvailableTimes)
                .orElse(Collections.emptyList());
    }
}
