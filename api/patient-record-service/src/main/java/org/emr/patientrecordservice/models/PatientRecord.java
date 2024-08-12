package org.emr.patientrecordservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "patient-records")
@Data
@NoArgsConstructor
public class PatientRecord {
    @Id
    private String id;

    private long patientId;
    private String medicalHistory;
    private List<Treatment> treatments;
    private List<LabResult> labs;
    private List<Visit> visits;

}
