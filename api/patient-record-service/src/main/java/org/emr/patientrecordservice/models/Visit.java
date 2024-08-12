package org.emr.patientrecordservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Visit {
    private LocalDate date;
    private String reason;
    private String notes;
    private long healthcareProviderId;
}
