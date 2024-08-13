package org.emr.patientrecordservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientNotification {
    private long patientId;
    private String type;
    private String message;
}
