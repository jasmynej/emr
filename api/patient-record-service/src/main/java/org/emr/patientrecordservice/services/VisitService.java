package org.emr.patientrecordservice.services;

import org.emr.patientrecordservice.models.PatientRecord;
import org.emr.patientrecordservice.models.Visit;
import org.emr.patientrecordservice.repos.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService {
    @Autowired
    private PatientRecordRepository patientRecordRepository;

    public List<Visit> allVisits(){
        List<PatientRecord> patientRecords = patientRecordRepository.findAll();
        List<Visit> allVisits = new ArrayList<>();

        for (PatientRecord record : patientRecords) {
            if (record.getVisits() != null) {
                allVisits.addAll(record.getVisits());
            }
        }

        return allVisits;
    }

    public List<Visit> visitsByProvider(long providerId) {
        List<Visit> allVisits = allVisits();
        List<Visit> visitsByProvider = new ArrayList<>();
        for (Visit visit : allVisits) {
            if(visit.getHealthcareProviderId() == providerId) {
                visitsByProvider.add(visit);
            }
        }
        return visitsByProvider;
    }
}
