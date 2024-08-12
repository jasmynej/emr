package org.emr.patientrecordservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LabResult {
    private String type;
    private String result;
    private LocalDate date;

}
