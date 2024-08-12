package org.emr.patientrecordservice.controllers;


import org.emr.patientrecordservice.models.LabResult;
import org.emr.patientrecordservice.models.PatientRecord;
import org.emr.patientrecordservice.models.Treatment;
import org.emr.patientrecordservice.models.Visit;
import org.emr.patientrecordservice.repos.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/patient-records")
@CrossOrigin("*")
public class PatientRecordController {
    @Autowired
    private PatientRecordRepository patientRecordRepository;

    @GetMapping("")
    public List<PatientRecord> getAllPatientRecords() {
        return patientRecordRepository.findAll();
    }

    @GetMapping("/{patientId}")
    public PatientRecord getPatientRecord(@PathVariable long patientId) {
        return patientRecordRepository.findByPatientId(patientId).orElse(null);
    }

    @PostMapping("/{patientId}/create-record")
    public PatientRecord createPatientRecord(@PathVariable long patientId) {
        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setPatientId(patientId);

        return patientRecordRepository.save(patientRecord);
    }

    @PutMapping("/{patientId}/medical-history")
    public PatientRecord updatePatientRecord(@PathVariable long patientId, @RequestBody String medicalHistory) {
        PatientRecord patientRecord = patientRecordRepository.findByPatientId(patientId).orElse(null);
        assert patientRecord != null;
        patientRecord.setMedicalHistory(medicalHistory);
        return patientRecordRepository.save(patientRecord);
    }

    @PutMapping("/{patientId}/add-treatment")
    public PatientRecord addTreatment(@PathVariable long patientId, @RequestBody Treatment treatment) {
        PatientRecord patientRecord = patientRecordRepository.findByPatientId(patientId).orElse(null);
        assert patientRecord != null;
        List<Treatment> treatments = patientRecord.getTreatments();
        if (treatments == null) {
            treatments = new ArrayList<>();
        }
        treatments.add(treatment);
        patientRecord.setTreatments(treatments);
        return patientRecordRepository.save(patientRecord);
    }

    @PutMapping("/{patientId}/add-visit")
    public PatientRecord addVisit(@PathVariable long patientId, @RequestBody Visit visit) {
        PatientRecord patientRecord = patientRecordRepository.findByPatientId(patientId).orElse(null);
        assert patientRecord != null;
        List<Visit> visits = patientRecord.getVisits();
        if (visits == null) {
            visits = new ArrayList<>();
        }
        visits.add(visit);
        patientRecord.setVisits(visits);
        return patientRecordRepository.save(patientRecord);
    }

    @PutMapping("/{patientId}/add-lab")
    public PatientRecord addLab(@PathVariable long patientId, @RequestBody LabResult lab) {
        PatientRecord patientRecord = patientRecordRepository.findByPatientId(patientId).orElse(null);
        assert patientRecord != null;
        List<LabResult> labs = patientRecord.getLabs();
        if (labs == null) {
            labs = new ArrayList<>();
        }
        labs.add(lab);
        patientRecord.setLabs(labs);
        return patientRecordRepository.save(patientRecord);
    }
}
