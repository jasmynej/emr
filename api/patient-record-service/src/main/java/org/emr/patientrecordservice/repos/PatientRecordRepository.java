package org.emr.patientrecordservice.repos;

import org.emr.patientrecordservice.models.PatientRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientRecordRepository extends MongoRepository<PatientRecord,String> {
    Optional<PatientRecord> findByPatientId(long patientId);
}
