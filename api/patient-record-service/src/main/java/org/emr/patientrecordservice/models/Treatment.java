package org.emr.patientrecordservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Treatment {
    private LocalDate date;
    private String type;
    private String description;
    private String status;
}
