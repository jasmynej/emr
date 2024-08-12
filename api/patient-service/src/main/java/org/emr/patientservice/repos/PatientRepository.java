package org.emr.patientservice.repos;

import org.emr.patientservice.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findByLastNameAndDateOfBirth(String lastName, LocalDate birthDate);
}
